package com.example.jetpack_compose_demo.ui.formValidation.presentation

sealed class RegistrationFormEvent{
    data class EmailChange(val email: String): RegistrationFormEvent()
    data class AcceptTerms(val isAccepted: Boolean): RegistrationFormEvent()

    object Submit: RegistrationFormEvent()
}