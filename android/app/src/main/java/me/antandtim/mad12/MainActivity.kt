package me.antandtim.mad12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import me.antandtim.mad12.card.adapter.CardAdapter
import me.antandtim.mad12.card.network.BaseClient.CARD_API_CLIENT
import me.antandtim.mad12.card.network.CardGetCallback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardRecycler.apply {
            adapter = cardAdapter()
            layoutManager = GridLayoutManager(this@MainActivity, 1)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun cardAdapter() = CardAdapter(this@MainActivity).apply {
        CARD_API_CLIENT.get().enqueue(CardGetCallback(this, this@MainActivity))
    }
}