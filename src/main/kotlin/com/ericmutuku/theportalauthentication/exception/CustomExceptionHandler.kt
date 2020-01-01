package com.ericmutuku.theportalauthentication.exception

import com.ericmutuku.theportalauthentication.model.ApiResponse
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.ericmutuku.theportalauthentication.util.Translator.Companion.toLocale
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.nio.file.AccessDeniedException
import java.util.logging.Level
import java.util.logging.Logger

@ControllerAdvice
class CustomExceptionHandler : ResponseEntityExceptionHandler() {
    private val TAG = CustomExceptionHandler::class.java.simpleName
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleConflictException(ex: DataIntegrityViolationException): ResponseEntity<Any> {
        Companion.logger.log(Level.SEVERE, " *** handleConflictException")
        Companion.logger.log(Level.SEVERE, ex.message)
        return ResponseEntity.status(HttpStatus.CONFLICT).body(getApiResponse(HttpStatus.CONFLICT, toLocale("msg.conflict-exception")))
    }

    @ExceptionHandler(value = [IllegalArgumentException::class, IllegalStateException::class])
    protected fun handleIllegalArgumentException(ex: RuntimeException): ResponseEntity<Any> {
        Companion.logger.log(Level.SEVERE, " *** handleIllegalArgumentException")
        Companion.logger.log(Level.SEVERE, ex.message)
        return if (ex is NumberFormatException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getApiResponse(HttpStatus.BAD_REQUEST, "Number Format Exception."))
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getApiResponse(HttpStatus.BAD_REQUEST, ex.localizedMessage))
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException::class)
    fun handleInvalidFormatException(ex: InvalidFormatException): ResponseEntity<Any> {
        Companion.logger.log(Level.SEVERE, " *** handleInvalidFormatException")
        Companion.logger.log(Level.SEVERE, ex.message)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getApiResponse(HttpStatus.BAD_REQUEST, "Invalid Data Format"))
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException::class)
    fun handleForbiddedException(ex: ForbiddenException): ResponseEntity<Any> {
        Companion.logger.log(Level.SEVERE, " *** handleForbiddedException")
        Companion.logger.log(Level.SEVERE, ex.message)
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(getApiResponse(HttpStatus.FORBIDDEN, "Permission Denied!"))
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(ex: AccessDeniedException): ResponseEntity<Any> {
        Companion.logger.log(Level.SEVERE, " *** handleAccessDeniedException")
        Companion.logger.log(Level.SEVERE, ex.message)
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(getApiResponse(HttpStatus.FORBIDDEN, "Access Denied!"))
    }

    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun handleEmptyResultDataAccessException(ex: EmptyResultDataAccessException): ResponseEntity<Any> {
        Companion.logger.log(Level.SEVERE, " *** handleEmptyResultDataAccessException")
        Companion.logger.log(Level.SEVERE, ex.message)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getApiResponse(HttpStatus.BAD_REQUEST, "Record does not exist!"))
    }

    private fun getApiResponse(httpStatus: HttpStatus, message: String): ApiResponse {
        val apiResponse = ApiResponse()
        apiResponse.status = httpStatus
        apiResponse.status_code = httpStatus.value()
        apiResponse.message = message
        return apiResponse
    }

    companion object {
        private val logger = Logger.getLogger(CustomExceptionHandler::class.java.simpleName)
    }
}