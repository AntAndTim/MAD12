package me.antandtim.mad12.card.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card.view.*
import me.antandtim.mad12.MainActivity
import me.antandtim.mad12.R
import me.antandtim.mad12.card.activity.CardActivity
import me.antandtim.mad12.card.model.Card
import me.antandtim.mad12.card.util.ExpirationBinder
import me.antandtim.mad12.card.util.bindExpireDate

class CardAdapter(
    private val mainActivity: MainActivity
) : ListAdapter<Card, CardViewHolder>(Card.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false),
            mainActivity
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CardViewHolder(itemView: View, private val mainActivity: MainActivity) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(card: Card) {
        itemView.cardName.text = card.name
        itemView.cardDescription.text = card.description
        bindExpireDate(card, mainActivity)
        itemView.completed.isChecked = card.completed
        itemView.completed.isEnabled = false

        itemView.setOnClickListener {
            mainActivity.startActivity(
                Intent(mainActivity, CardActivity::class.java)
                    .putExtra(Card.nameIntentName, card.name)
                    .putExtra(Card.descriptionIntentName, card.description)
                    .putExtra(Card.expireDateIntentName, card.expireDate.toEpochMilli())
                    .putExtra(Card.completedIntentName, card.completed)
            )
        }
    }

    private fun bindExpireDate(
        card: Card,
        mainActivity: MainActivity
    ) = object : ExpirationBinder {
        override fun bind(expirationTime: String) {
            itemView.timeLeft.text = mainActivity.getString(
                R.string.card_time_left_text,
                expirationTime.substring(0, expirationTime.length - 3)
            )
        }
    }.bindExpireDate(card.expireDate, interval = 60000)
}