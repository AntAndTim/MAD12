package me.antandtim.mad12.user.provider

import me.antandtim.mad12.user.repository.UserRepository
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserProvider(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : AuthenticationProvider {
    
    override fun authenticate(authentication: Authentication): Authentication? {
        userRepository.findByLogin(authentication.name)?.run {
            if (passwordEncoder.matches(authentication.credentials.toString(), password)) {
                return UsernamePasswordAuthenticationToken(
                    authentication.name,
                    authentication.credentials,
                    emptyList()
                )
            }
        }
        return null
    }
    
    override fun supports(authentication: Class<*>) =
        UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
}