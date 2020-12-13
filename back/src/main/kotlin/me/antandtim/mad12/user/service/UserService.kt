package me.antandtim.mad12.user.service

import me.antandtim.mad12.user.exception.NoSuchUserException
import me.antandtim.mad12.user.exception.UserAlreadyExistsException
import me.antandtim.mad12.user.mapper.UserMapper
import me.antandtim.mad12.user.model.UserDto
import me.antandtim.mad12.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val passwordEncoder: PasswordEncoder
) {
    
    @Transactional
    fun register(userDto: UserDto): UserDto {
        userDto.login?.let { username ->
            userRepository.findByLogin(username)?.let {
                throw UserAlreadyExistsException()
            }
        }
        
        return userMapper.map(
            userRepository.save(
                userMapper
                    .map(userDto)
                    .also { it.password = passwordEncoder.encode(it.password) }
            )
        )
    }
    
    fun getInfo(login: String) =
        userMapper.map(userRepository.findByLogin(login) ?: throw NoSuchUserException())
    
    fun get(login: String) =
        userRepository.findByLogin(login) ?: throw NoSuchUserException()
}