package me.antandtim.mad12.user.mapper

import me.antandtim.mad12.user.model.User
import me.antandtim.mad12.user.model.UserDto
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface UserMapper {
    
    @Mapping(source = "password", target = "password", ignore = true)
    fun map(user: User): UserDto
    
    fun map(userDto: UserDto): User
}