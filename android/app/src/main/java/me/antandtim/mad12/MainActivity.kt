package me.antandtim.mad12

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import me.antandtim.mad12.card.adapter.CardAdapter
import me.antandtim.mad12.card.network.CARD_API_CLIENT
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

    private fun cardAdapter() = CardAdapter(this@MainActivity).also {
        object : CountDownTimer(
            Long.MAX_VALUE,
            5000
        ) {
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinished: Long) {
                CARD_API_CLIENT.get().enqueue(CardGetCallback(it, this@MainActivity))
            }
        }.start()
    }
}