package me.antandtim.mad12.card.exception

import me.antandtim.mad12.common.exception.AppException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

private const val message = "You cannot access this card with this user"

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
class IllegalCardAccessException : RuntimeException(message)

@ControllerAdvice
class IllegalCardAccessExceptionAdvice {
    
    @ExceptionHandler(IllegalCardAccessException::class)
    fun handleException(e: IllegalCardAccessException) = ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(AppException(message))
}