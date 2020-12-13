package me.antandtim.mad12

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import me.antandtim.mad12.auth.data.LoginRepository
import me.antandtim.mad12.auth.ui.login.AuthorizationActivity
import me.antandtim.mad12.auth.ui.login.LoginViewModel
import me.antandtim.mad12.card.activity.CreateCardActivity
import me.antandtim.mad12.card.adapter.CardAdapter
import me.antandtim.mad12.card.network.CARD_API_CLIENT
import me.antandtim.mad12.card.network.CardGetRefreshCallback
import me.antandtim.mad12.sharedpreferences.SharedPreferencesWrapper
import javax.inject.Inject
import javax.inject.Named


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var loginRepository: LoginRepository

    lateinit var cardAdapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as CardApplication).appComponent.injectMain(this)

        if(!loginRepository.isLoggedIn){
            val intent = Intent(this, AuthorizationActivity::class.java)
            startActivity(intent)
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
    }

    override fun onResume() {
        super.onResume()
        refreshData(cardAdapter)
    }

    private fun refreshData(
        cardAdapter: CardAdapter
    ) {
        CARD_API_CLIENT.get().enqueue(
            CardGetRefreshCallback(
                cardAdapter,
                this@MainActivity
            )
        )
    }

    private fun cardAdapter() = CardAdapter(this@MainActivity).also {
        CARD_API_CLIENT.get().enqueue(CardGetRefreshCallback(it, this@MainActivity))
    }
}