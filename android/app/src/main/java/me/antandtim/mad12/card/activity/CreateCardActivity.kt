package me.antandtim.mad12.card.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_card.*
import me.antandtim.mad12.R
import me.antandtim.mad12.card.model.Card
import me.antandtim.mad12.card.network.CARD_API_CLIENT
import me.antandtim.mad12.card.network.CardCreateCallback

class CreateCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Create Card"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        createCardButton.setOnClickListener {
            it.isEnabled = false
            CARD_API_CLIENT.create(
                Card(
                    name = createCardName.text.toString(),
                    description = createCardDescription.text.toString()
                )
            ).enqueue(CardCreateCallback(this))
        }
    }

    fun onSuccess() {
        finish()
    }

    fun onFailure() {
        createCardButton.isEnabled = true
    }
}