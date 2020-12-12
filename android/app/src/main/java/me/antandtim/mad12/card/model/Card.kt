package me.antandtim.mad12.card.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import java.time.Instant

data class Card(
    val id: Long? = null,
    val name: String,
    val description: String,
    @SerializedName("expireTime") // Sorry, I forgot the field name while writing this code
    val expireDate: Instant? = null,
    val completed: Boolean? = null
) {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Card> =
            object : DiffUtil.ItemCallback<Card>() {
                override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }

                override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            }

        const val idIntentName = "ID"
        const val nameIntentName = "NAME"
        const val descriptionIntentName = "DESCRIPTION"
        const val expireDateIntentName = "EXPIRE_DATE"
        const val completedIntentName = "COMPLETED"
    }
}