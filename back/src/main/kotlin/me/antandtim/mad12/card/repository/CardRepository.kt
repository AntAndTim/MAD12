package me.antandtim.mad12.card.repository

import me.antandtim.mad12.card.model.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CardRepository : JpaRepository<Card, Long>, JpaSpecificationExecutor<Card>