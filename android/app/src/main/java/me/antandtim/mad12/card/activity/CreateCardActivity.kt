package me.antandtim.mad12.card.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_card.*
import me.antandtim.mad12.CardApplication
import me.antandtim.mad12.R
import me.antandtim.mad12.card.model.Card
import me.antandtim.mad12.card.network.CardApiClient
import me.antandtim.mad12.card.network.CardCreateCallback
import javax.inject.Inject

class CreateCardActivity : AppCompatActivity() {

    @Inject
    lateinit var cardApiClient: CardApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)
        (application as CardApplication).appComponent.injectCreateCardActivity(this)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Create Card"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        createCardButton.setOnClickListener {
            it.isEnabled = false
            cardApiClient.create(
                Card(
                    name = createCardName.text.toString(),
                    description = createCardDescription.text.toString()
                )
            ).enqueue(CardCreateCallback(this))
        }

        createCardName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                createCardButton.isEnabled = s!!.isNotBlank()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    fun onSuccess() {
        finish()
    }

    fun onFailure() {
        createCardButton.isEnabled = true
    }
}