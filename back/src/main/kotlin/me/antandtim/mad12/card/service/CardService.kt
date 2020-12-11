package me.antandtim.mad12.card.service

import me.antandtim.mad12.card.exception.CardExpiredException
import me.antandtim.mad12.card.exception.NoSuchCardException
import me.antandtim.mad12.card.mapper.CardMapper
import me.antandtim.mad12.card.model.CardDto
import me.antandtim.mad12.card.repository.CardRepository
import org.springframework.stereotype.Service

@Service
class CardService(private val repository: CardRepository, private val mapper: CardMapper) {
    
    fun create(cardDto: CardDto) = mapper.map(repository.save(mapper.map(cardDto)))
    
    fun get() = repository.findAll().mapNotNull(mapper::map)
    
    fun get(id: Long) = mapper.map(
        repository
            .findById(id)
            .orElseThrow { NoSuchCardException() }
    )
    
    fun complete(id: Long): CardDto {
        val card = repository
            .findById(id)
            .orElseThrow { NoSuchCardException() }
        
        if (card.expired()) throw CardExpiredException()
        
        return mapper.map(repository.save(card.apply { completed = true }))
    }
}