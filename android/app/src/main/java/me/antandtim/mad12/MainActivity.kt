package me.antandtim.mad12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import me.antandtim.mad12.card.adapter.CardAdapter
import me.antandtim.mad12.card.model.Card
import java.util.stream.Collectors
import java.util.stream.IntStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mutableListOf = mutableListOf<Card>()

        for (i in 0..20) {
            mutableListOf.add(
                Card(
                    "Name$i",
                    "Description",
                    "date",
                    true
                )
            )
        }

        cardRecycler.apply {
            adapter = CardAdapter(this@MainActivity).apply {
                submitList(mutableListOf)
            }
            layoutManager = GridLayoutManager(this@MainActivity, 1)
            itemAnimator = DefaultItemAnimator()
        }
    }
}