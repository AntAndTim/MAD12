package me.antandtim.mad12.card.exception

import me.antandtim.mad12.common.exception.AppException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

private const val message = "Card is expired"

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class CardExpiredException : RuntimeException(message)

@ControllerAdvice
class CardExpiredExceptionHandlerAdvice {
    
    @ExceptionHandler(CardExpiredException::class)
    fun handleException(e: CardExpiredException) = ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(AppException(message))
}