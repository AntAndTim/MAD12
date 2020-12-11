package me.antandtim.mad12.card.mapper

import me.antandtim.mad12.card.model.Card
import me.antandtim.mad12.card.model.CardDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface CardMapper {
    
    fun map(card: Card): CardDto
    
    fun map(cardDto: CardDto): Card
}