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
import java.time.Instant

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
        if (card.description.isBlank()) {
            itemView.cardDescription.visibility = View.GONE
        } else {
            itemView.cardDescription.text = card.description
        }

        if (card.completed != true) {
            bindExpireDate(card)
        } else {
            itemView.timeLeft.visibility = View.GONE
        }

        itemView.setOnClickListener {
            mainActivity.startActivity(
                Intent(mainActivity, CardActivity::class.java)
                    .putExtra(Card.idIntentName, card.id)
                    .putExtra(Card.nameIntentName, card.name)
                    .putExtra(Card.descriptionIntentName, card.description)
                    .putExtra(Card.expireDateIntentName, card.expireDate?.toEpochMilli())
                    .putExtra(Card.completedIntentName, card.completed)
            )
        }
    }

    private fun bindExpireDate(card: Card) = object : ExpirationBinder {
        override fun bind(expirationTime: String) {
            itemView.timeLeft.text = expirationTime.split(":").let { "${it[0]}h ${it[1]}m" }
        }
    }.bindExpireDate(card.expireDate ?: Instant.now(), interval = 60000).start()
}