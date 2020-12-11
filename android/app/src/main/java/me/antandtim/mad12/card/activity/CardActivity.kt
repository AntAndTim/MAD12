package me.antandtim.mad12.card.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_card.*
import me.antandtim.mad12.R
import me.antandtim.mad12.card.model.Card

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        cardName.text = intent.getStringExtra(Card.nameIntentName)
        cardDescription.text = intent.getStringExtra(Card.descriptionIntentName)
        cardExpireDate.text = intent.getStringExtra(Card.expireDateIntentName)
    }
}