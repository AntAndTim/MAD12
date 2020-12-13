package me.antandtim.mad12.user.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "REAL_WISH_USER")
class User {
    
    @Id
    @GeneratedValue(generator = "userSequenceGenerator")
    @GenericGenerator(
        name = "userSequenceGenerator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator"
    )
    var id: Long? = null
    
    @Column(unique = true)
    var login: String? = null
    
    @Column
    var password: String? = null
}