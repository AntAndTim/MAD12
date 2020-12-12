package me.antandtim.mad12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import me.antandtim.mad12.card.adapter.CardAdapter
import me.antandtim.mad12.card.network.CARD_API_CLIENT
import me.antandtim.mad12.card.network.CardGetRefreshCallback


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardContainerSwipe.isRefreshing = true

        cardAdapter().let {
            cardContainerSwipe.setOnRefreshListener { refreshData(it) }

            cardRecycler.apply {
                adapter = it
                layoutManager = GridLayoutManager(this@MainActivity, 1)
                itemAnimator = DefaultItemAnimator()
            }
        }
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