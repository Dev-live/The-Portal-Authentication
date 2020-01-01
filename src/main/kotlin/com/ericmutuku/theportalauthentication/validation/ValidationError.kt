package com.ericmutuku.theportalauthentication.validation

import com.fasterxml.jackson.annotation.JsonInclude
import lombok.Getter

@Getter
class ValidationError(val errorMessage: String) {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val errors = mutableListOf<String>()

    /**
     *
     * @param error String
     */
    fun addValidationError(error: String) {
        errors.add(error)
    }

}
