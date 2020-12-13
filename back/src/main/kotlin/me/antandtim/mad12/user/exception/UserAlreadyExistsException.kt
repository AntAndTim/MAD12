package me.antandtim.mad12.user.exception

import me.antandtim.mad12.common.exception.AppException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

private const val message = "This user already exists"

@ResponseStatus(value = HttpStatus.CONFLICT)
class UserAlreadyExistsException : RuntimeException(message)

@ControllerAdvice
class UserAlreadyExistsExceptionHandlerAdvice {
    
    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleException(e: UserAlreadyExistsException) = ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(AppException(message))
}