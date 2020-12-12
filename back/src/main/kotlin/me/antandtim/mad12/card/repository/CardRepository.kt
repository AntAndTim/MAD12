package me.antandtim.mad12.card.repository

import me.antandtim.mad12.card.model.Card
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository : JpaRepository<Card, Long> {
    
    fun findByCompleted(completed: Boolean): List<Card>
}