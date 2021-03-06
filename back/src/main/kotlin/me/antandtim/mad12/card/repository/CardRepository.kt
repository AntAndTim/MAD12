package me.antandtim.mad12.card.repository

import me.antandtim.mad12.card.model.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface CardRepository : JpaRepository<Card, Long>, JpaSpecificationExecutor<Card>