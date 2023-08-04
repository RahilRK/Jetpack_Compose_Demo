package com.example.jetpack_compose_demo.ui.formValidation.domain.use_case

import android.util.Patterns
import com.example.jetpack_compose_demo.ui.formValidation.util.ValidationResult
import javax.inject.Inject

class ValidateEmail @Inject constructor() {

    fun execute(email: String): ValidationResult {
        if (email.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Enter Email Id"
            )
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Invalid Email Id"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}