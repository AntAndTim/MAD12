package me.antandtim.mad12.user.exception

import me.antandtim.mad12.common.exception.AppException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

private const val message = "These are not the droids you are looking for"

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class NoSuchUserException : RuntimeException(message)

@ControllerAdvice
class NoSuchUserExceptionHandlerAdvice {
    
    @ExceptionHandler(NoSuchUserException::class)
    fun handleException(e: NoSuchUserException) = ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(AppException(message))
}