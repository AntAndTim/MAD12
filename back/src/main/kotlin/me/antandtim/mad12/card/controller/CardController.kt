package me.antandtim.mad12.card.controller

import me.antandtim.mad12.card.model.CardDto
import me.antandtim.mad12.card.model.CardFilter
import me.antandtim.mad12.card.service.CardService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/card")
class CardController(private val service: CardService) {
    
    @PostMapping
    fun create(@RequestBody cardDto: CardDto) = service.create(cardDto)
    
    @GetMapping
    fun get(cardFilter: CardFilter) = service.get(cardFilter)
    
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = service.get(id)
    
    @PutMapping("/complete/{id}")
    fun complete(@PathVariable id: Long) = service.complete(id)
}