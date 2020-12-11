package me.antandtim.mad12.card.model

import androidx.recyclerview.widget.DiffUtil

data class Card(
    val name: String,
    val description: String,
    val expireDate: String,
    val completed: Boolean
) {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Card> =
            object : DiffUtil.ItemCallback<Card>() {
                override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
                    return oldItem.name == newItem.name
                }
            }

        const val nameIntentName = "NAME"
        const val descriptionIntentName = "DESCRIPTION"
        const val expireDateIntentName = "EXPIRE_DATE"
        const val completedIntentName = "COMPLETED"
    }
}