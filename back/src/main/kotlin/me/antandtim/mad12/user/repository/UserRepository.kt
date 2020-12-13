package me.antandtim.mad12.user.repository

import me.antandtim.mad12.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    
    fun findByLogin(login: String): User?
}