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

//        createNotificationChannel()

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

//            val intent = Intent(this, CardActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                putExtra(Card.idIntentName, intent.getLongExtra(Card.idIntentName, 1))
//                putExtra(Card.nameIntentName, intent.getStringExtra(Card.nameIntentName))
//                putExtra(Card.descriptionIntentName, intent.getStringExtra(Card.descriptionIntentName))
//                putExtra(Card.expireDateIntentName, intent.getSerializableExtra(Card.expireDateIntentName) )
//                putExtra(Card.completedIntentName, intent.getBooleanExtra(Card.completedIntentName,false))
//            }
//            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
//
//            var builder = NotificationCompat.Builder(this, CHANNEL_ID)
//                    .setSmallIcon(R.drawable.ic_baseline_add_24)
//                    .setContentTitle("My notification")
//                    .setContentText("Much longer text that cannot fit one line...")
//                    .setStyle(NotificationCompat.BigTextStyle()
//                            .bigText("Much longer text that cannot fit one line..."))
//                    .setContentIntent(pendingIntent)
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            countDownTimer = object : ExpirationBinder {
                override fun bind(expirationTime: String) {
                    timeLeft.text = expirationTime
//                    Log.e("TAG", timeLeft.text.toString())
//                    Log.e("TAG1", timeLeft.text.toString().equals("23:59:59").toString())
//
//                    if (timeLeft.text.toString() == "23:59:55") {
//                        Log.e("TAG2", timeLeft.text.toString())
//                        notificationManager.notify(NOTIFY_ID, builder.build())
//                    }
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

//    private fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = getString(R.string.channel_name)
//            val descriptionText = getString(R.string.channel_description)
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                description = descriptionText
//            }
//            notificationManager =
//                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
}