package com.example.jetpack_compose_demo.ui.formValidation.presentation

data class RegistrationFormState(
    val email: String = "",
    val emailError: String? = null,
    val acceptTerms: Boolean = false,
    val termsError: String? = null,
)
