package me.antandtim.mad12.user.model

import io.swagger.annotations.ApiModelProperty

class UserDto {
    
    @ApiModelProperty("User's login")
    var login: String? = null
    
    @ApiModelProperty("User's password")
    var password: String? = null
}