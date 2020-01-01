package com.ericmutuku.theportalauthentication.validation

import com.ericmutuku.theportalauthentication.validation.ValidationError
import org.springframework.validation.Errors

object ValidationErrorBuilder {
    /**
     *
     * @param errors Errors
     * @return ValidationError
     */
    fun fromBindingErrors(errors: Errors): ValidationError {
        val error = ValidationError("Validation failed with " + errors.errorCount + " error(s)")
        for (objectError in errors.allErrors) {
            error.addValidationError(objectError.defaultMessage!!)
        }
        return error
    }
}