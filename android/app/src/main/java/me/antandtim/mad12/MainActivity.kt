package me.antandtim.mad12

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import me.antandtim.mad12.authentication.activity.AuthenticationActivity
import me.antandtim.mad12.card.activity.CreateCardActivity
import me.antandtim.mad12.card.adapter.CardAdapter
import me.antandtim.mad12.card.network.CardApiClient
import me.antandtim.mad12.card.network.CardGetRefreshCallback
import me.antandtim.mad12.common.activity.interaction.RequestCode
import me.antandtim.mad12.sharedpreferences.SharedPreferencesWrapper
import javax.inject.Inject
import javax.inject.Named


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var cardApiClient: CardApiClient

    @Inject
    lateinit var preferencesWrapper: SharedPreferencesWrapper

    lateinit var cardAdapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as CardApplication).appComponent.injectMain(this)

        if (preferencesWrapper.getString("LOGIN") == "") {
            startActivityForResult(
                Intent(this, AuthenticationActivity::class.java),
                RequestCode.AUTHENTICATION.code
            )
        }


        cardContainerSwipe.isRefreshing = true

        cardAdapter = cardAdapter()

        cardContainerSwipe.setOnRefreshListener { refreshData(cardAdapter) }

        cardRecycler.apply {
            adapter = cardAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 1)
            itemAnimator = DefaultItemAnimator()
        }

        fab.setOnClickListener {
            startActivity(Intent(this, CreateCardActivity::class.java))
        }

        logout_btn.setOnClickListener {
            preferencesWrapper.set("LOGIN", "")
            preferencesWrapper.set("PASSWORD", "")
            startActivityForResult(
                    Intent(this, AuthenticationActivity::class.java),
                    RequestCode.AUTHENTICATION.code
            )
        }
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
                this@MainActivity
            )
        )
    }

    private fun cardAdapter() = CardAdapter(this@MainActivity).also {
        cardApiClient.get().enqueue(CardGetRefreshCallback(it, this@MainActivity))
    }
}