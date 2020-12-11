package me.antandtim.mad12.card.model

import io.swagger.annotations.ApiModelProperty
import java.time.Instant
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CardDto(
    
    @ApiModelProperty("Id of the card", example = "1", hidden = true)
    val id: Long? = null,
    
    @NotBlank
    @ApiModelProperty("Name of the card", example = "Buy apples")
    val name: String,
    
    @ApiModelProperty(
        "Description of the card",
        example = "I need to by some apples to become healthy"
    )
    val description: String? = null,
    
    @ApiModelProperty("Time when the card will expire")
    val expireTime: Instant? = null,
    
    @NotNull
    @ApiModelProperty("Is card completed")
    val completed: Boolean
)