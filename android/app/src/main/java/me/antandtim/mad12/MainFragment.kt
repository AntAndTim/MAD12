package me.antandtim.mad12

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_name.*
import kotlinx.android.synthetic.main.fragment_name.view.*
import me.antandtim.mad12.authentication.activity.AuthenticationActivity
import me.antandtim.mad12.card.activity.CreateCardActivity
import me.antandtim.mad12.card.adapter.CardAdapter
import me.antandtim.mad12.card.network.CardApiClient
import me.antandtim.mad12.card.network.CardGetRefreshCallback
import me.antandtim.mad12.common.activity.interaction.RequestCode
import me.antandtim.mad12.sharedpreferences.SharedPreferencesWrapper
import javax.inject.Inject


class MainFragment : Fragment() {
    @Inject
    lateinit var cardApiClient: CardApiClient

    @Inject
    lateinit var preferencesWrapper: SharedPreferencesWrapper

    lateinit var cardAdapter: CardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_name, container, false)

        (activity?.application as CardApplication).appComponent.injectMain(this)

        if (preferencesWrapper.getString("LOGIN") == "") {
            startActivityForResult(
                    Intent(activity, AuthenticationActivity::class.java),
                    RequestCode.AUTHENTICATION.code
            )
        }


        view.cardContainerSwipe.isRefreshing = true

        cardAdapter = cardAdapter()

        view.cardContainerSwipe.setOnRefreshListener { refreshData(cardAdapter) }

        view.cardRecycler.apply {
            adapter = cardAdapter
            layoutManager = GridLayoutManager(activity, 1)
            itemAnimator = DefaultItemAnimator()
        }

        view.add.setOnClickListener {
            startActivity(Intent(activity, CreateCardActivity::class.java))
        }
        return view
    }


    override fun onResume() {
        super.onResume()
        refreshData(cardAdapter)
    }

    private fun refreshData(
            cardAdapter: CardAdapter
    ) {
        cardApiClient.get().enqueue(
                CardGetRefreshCallback(
                        cardAdapter,
                        this@MainFragment
                )
        )
    }

    private fun cardAdapter() = CardAdapter(this@MainFragment).also {
        cardApiClient.get().enqueue(CardGetRefreshCallback(it, this@MainFragment))
    }
}