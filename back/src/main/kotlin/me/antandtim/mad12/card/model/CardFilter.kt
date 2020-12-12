package me.antandtim.mad12.card.model

import io.swagger.annotations.ApiModelProperty
import me.antandtim.mad12.common.util.SpecificationExclusion

data class CardFilter(
    
    @ApiModelProperty("Is card completed")
    val completed: Boolean?,
    
    @SpecificationExclusion
    @ApiModelProperty("Should we exclude the cards which already have expired")
    val excludeExpired: Boolean? = true
)