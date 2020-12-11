package me.antandtim.mad12.card.exception

import me.antandtim.mad12.common.exception.AppException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

private const val message = "There is no such card"

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class NoSuchCardException : RuntimeException(message)

@ControllerAdvice
class NoSuchCardExceptionHandlerAdvice {
    
    @ExceptionHandler(NoSuchCardException::class)
    fun handleException(e: NoSuchCardException) = ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(AppException(message))
}