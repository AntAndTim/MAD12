package me.antandtim.mad12

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import me.antandtim.mad12.card.activity.CreateCardActivity
import me.antandtim.mad12.card.adapter.CardAdapter
import me.antandtim.mad12.card.network.CARD_API_CLIENT
import me.antandtim.mad12.card.network.CardGetRefreshCallback


class MainActivity : AppCompatActivity() {

    lateinit var cardAdapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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