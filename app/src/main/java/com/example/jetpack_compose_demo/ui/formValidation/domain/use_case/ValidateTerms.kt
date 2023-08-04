package com.example.jetpack_compose_demo.ui.formValidation.domain.use_case

import com.example.jetpack_compose_demo.ui.formValidation.util.ValidationResult
import javax.inject.Inject

class ValidateTerms @Inject constructor(){

    fun execute(acceptedTerms: Boolean): ValidationResult {
        if (!acceptedTerms) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please accept the terms"
            )
        }
        
        return ValidationResult(
            successful = true
        )
    }
}