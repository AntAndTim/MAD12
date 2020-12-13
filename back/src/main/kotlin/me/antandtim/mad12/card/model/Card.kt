package me.antandtim.mad12.card.model

import me.antandtim.mad12.user.model.User
import org.hibernate.annotations.GenericGenerator
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.persistence.*

@Entity
@Table
class Card {
    
    @Id
    @GeneratedValue(generator = "cardSequenceGenerator")
    @GenericGenerator(
        name = "cardSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator"
    )
    var id: Long? = null
    
    @Column
    var name: String? = null
    
    @Column
    var description: String? = null
    
    @Column
    var expireTime: Instant? = Instant.now().plus(1, ChronoUnit.DAYS)
        set(value) {
            if (value != null) {
                field = value
            }
        }
    
    @Column
    var completed: Boolean? = null
    
    @ManyToOne
    var user: User? = null
    
    fun expired() = Instant.now().isAfter(expireTime)
}