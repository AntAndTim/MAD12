package me.antandtim.mad12.user.controller

import me.antandtim.mad12.user.model.UserDto
import me.antandtim.mad12.user.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(val userService: UserService) {
    
    @PostMapping
    fun register(@RequestBody userDto: UserDto) = userService.register(userDto)
    
    @GetMapping("/info")
    fun info(authentication: Authentication): UserDto {
        return userService.get(authentication.name)
    }
}