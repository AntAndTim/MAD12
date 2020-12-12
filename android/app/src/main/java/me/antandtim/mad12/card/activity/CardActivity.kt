package me.antandtim.mad12.card.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_card.*
import me.antandtim.mad12.R
import me.antandtim.mad12.card.model.Card
import me.antandtim.mad12.card.util.ExpirationBinder
import me.antandtim.mad12.card.util.bindExpireDate
import java.time.Instant

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        cardName.text = intent.getStringExtra(Card.nameIntentName)
        cardDescription.text = intent.getStringExtra(Card.descriptionIntentName)

        object : ExpirationBinder {
            override fun bind(expirationTime: String) {
                timeLeft.text = expirationTime
            }
        }.bindExpireDate(Instant.ofEpochMilli(intent.getLongExtra(Card.expireDateIntentName, 0)))
    }
}