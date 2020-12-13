package me.antandtim.mad12.card.controller

import me.antandtim.mad12.card.model.CardDto
import me.antandtim.mad12.card.model.CardFilter
import me.antandtim.mad12.card.service.CardService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/card")
class CardController(private val service: CardService) {
    
    @PostMapping
    fun create(@RequestBody cardDto: CardDto, authentication: Authentication) =
        service.create(cardDto, authentication)
    
    @GetMapping
    fun get(cardFilter: CardFilter, authentication: Authentication) =
        service.get(cardFilter, authentication)
    
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long, authentication: Authentication) =
        service.get(id, authentication)
    
    @PutMapping("/complete/{id}")
    fun complete(@PathVariable id: Long, authentication: Authentication) =
        service.complete(id, authentication)
}