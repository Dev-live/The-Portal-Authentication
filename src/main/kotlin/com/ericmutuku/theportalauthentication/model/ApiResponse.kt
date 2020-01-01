package com.ericmutuku.theportalauthentication.model

import com.ericmutuku.theportalauthentication.util.Translator.Companion.toLocale
import org.springframework.http.HttpStatus

class ApiResponse {
    var status // HTTP status questionCode
            : HttpStatus? = null
    var message // Error message associated with exception
            : String? = null
    var status_code = 0
    var errors //List of constructed error messages
            : List<String>? = null
    var data: Any? = null

    constructor() {}
    private constructor(status: HttpStatus, httpStatusCode: Int, message: String, errors: List<String>) : super() {
        this.status = status
        status_code = httpStatusCode
        this.message = message
        this.errors = errors
    }

    private constructor(status: HttpStatus, httpStatusCode: Int, message: String, data: Any) : super() {
        this.status = status
        status_code = httpStatusCode
        this.message = message
        this.data = data
    }

    private constructor(status: HttpStatus, httpStatusCode: Int, message: String) : super() {
        this.status = status
        status_code = httpStatusCode
        this.message = message
    }

    companion object {
        /**
         * Build and return Field Error response object
         */
        fun fieldErrorResponse(errors: List<String>): ApiResponse {
            return ApiResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), toLocale("msg.field-errors"), errors)
        }

        /**
         * Build and report Success response object
         */
        fun okResponse(): ApiResponse {
            return ApiResponse(HttpStatus.OK, HttpStatus.OK.value(), toLocale("msg.success"))
        }

        /**
         * Build and report Success response object with data
         */
        fun okResponseWithData(data: Any): ApiResponse {
            return ApiResponse(HttpStatus.OK, HttpStatus.OK.value(), toLocale("msg.success"), data)
        }
    }
}