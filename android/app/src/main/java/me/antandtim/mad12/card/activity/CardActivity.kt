package me.antandtim.mad12.card.activity


import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_card.*
import me.antandtim.mad12.CardApplication
import me.antandtim.mad12.R
import me.antandtim.mad12.card.model.Card
import me.antandtim.mad12.card.network.CardApiClient
import me.antandtim.mad12.card.network.CardCompleteCallback
import me.antandtim.mad12.card.util.ExpirationBinder
import me.antandtim.mad12.card.util.bindExpireDate
import java.time.Instant
import javax.inject.Inject

class CardActivity : AppCompatActivity() {
    lateinit var countDownTimer: CountDownTimer

    @Inject
    lateinit var cardApiClient: CardApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        (application as CardApplication).appComponent.injectCardActivity(this)


        cardName.text = intent.getStringExtra(Card.nameIntentName)
        cardDescription.text = intent.getStringExtra(Card.descriptionIntentName)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = intent.getStringExtra(Card.nameIntentName)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

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
                cardApiClient
                        .complete(intent.getLongExtra(Card.idIntentName, -1))
                        .enqueue(CardCompleteCallback(this))
            }
        }
    }

    fun onComplete() {
        if (this::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }
        timeLeft.text = getString(R.string.completed)
        doneButton.isEnabled = false
    }

}