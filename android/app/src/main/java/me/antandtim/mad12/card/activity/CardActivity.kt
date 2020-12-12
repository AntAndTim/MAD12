package me.antandtim.mad12.card.activity

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_card.*
import me.antandtim.mad12.R
import me.antandtim.mad12.card.model.Card
import me.antandtim.mad12.card.network.CARD_API_CLIENT
import me.antandtim.mad12.card.network.CardCompleteCallback
import me.antandtim.mad12.card.util.ExpirationBinder
import me.antandtim.mad12.card.util.bindExpireDate
import java.time.Instant

class CardActivity : AppCompatActivity() {
    lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        cardName.text = intent.getStringExtra(Card.nameIntentName)
        cardDescription.text = intent.getStringExtra(Card.descriptionIntentName)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (intent.getBooleanExtra(Card.completedIntentName, true)) {
            onComplete()
        } else {
            countDownTimer = object : ExpirationBinder {
                override fun bind(expirationTime: String) {
                    timeLeft.text = expirationTime
                }
            }.bindExpireDate(
                Instant.ofEpochMilli(
                    intent.getLongExtra(
                        Card.expireDateIntentName,
                        0
                    )
                )
            ).start()

            doneButton.setOnClickListener {
                CARD_API_CLIENT
                    .complete(intent.getLongExtra(Card.idIntentName, -1))
                    .enqueue(CardCompleteCallback(this))
            }
        }
    }

    fun onComplete() {
        if (this::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }
        timeLeft.text = "Completed!"
        doneButton.isEnabled = false
    }
}