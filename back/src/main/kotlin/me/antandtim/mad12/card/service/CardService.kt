package me.antandtim.mad12.card.service

import me.antandtim.mad12.card.exception.CardExpiredException
import me.antandtim.mad12.card.exception.IllegalCardAccessException
import me.antandtim.mad12.card.exception.NoSuchCardException
import me.antandtim.mad12.card.mapper.CardMapper
import me.antandtim.mad12.card.model.Card
import me.antandtim.mad12.card.model.CardDto
import me.antandtim.mad12.card.model.CardFilter
import me.antandtim.mad12.card.repository.CardRepository
import me.antandtim.mad12.common.util.toSpecification
import me.antandtim.mad12.user.model.User
import me.antandtim.mad12.user.service.UserService
import org.springframework.data.jpa.domain.Specification
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class CardService(
    private val repository: CardRepository,
    private val mapper: CardMapper,
    private val userService: UserService
) {
    
    fun create(
        cardDto: CardDto,
        authentication: Authentication
    ) = mapper.map(
        repository.save(
            mapper
                .map(cardDto)
                .apply { user = userService.get(authentication.name) }
        )
    )
    
    fun get(cardFilter: CardFilter, authentication: Authentication) = repository
        .findAll(
            cardFilter
                .toSpecification<Card, CardFilter>()
                .and(userSpecification(userService.get(authentication.name)))
        )
        .filter(expiredCardFilter(cardFilter))
        .mapNotNull(mapper::map)
    
    fun get(id: Long, authentication: Authentication): CardDto {
        val card = repository
            .findById(id)
            .orElseThrow { NoSuchCardException() }
        
        if (userService.get(authentication.name).login != card.user?.login) {
            throw IllegalCardAccessException()
        }
        
        return mapper.map(card)
    }
    
    fun complete(id: Long, authentication: Authentication): CardDto {
        val card = repository
            .findById(id)
            .orElseThrow { NoSuchCardException() }
        
        if (userService.get(authentication.name).login != card.user?.login) {
            throw IllegalCardAccessException()
        }
        
        if (card.expired()) throw CardExpiredException()
        
        return mapper.map(repository.save(card.apply { completed = true }))
    }
    
    companion object {
        private fun expiredCardFilter(cardFilter: CardFilter): (Card) -> Boolean = {
            if (cardFilter.excludeExpired != null) {
                if (cardFilter.excludeExpired) !it.expired() else true
            } else {
                true
            }
        }
        
        private fun userSpecification(user: User) =
            Specification.where<Card> { root, _, cb -> cb.equal(root.get<User>("user"), user) }
    }
}