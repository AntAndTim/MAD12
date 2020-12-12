package me.antandtim.mad12.card.model

import io.swagger.annotations.ApiModelProperty

data class CardFilter(
    @ApiModelProperty("Is card completed")
    val completed: Boolean?
)