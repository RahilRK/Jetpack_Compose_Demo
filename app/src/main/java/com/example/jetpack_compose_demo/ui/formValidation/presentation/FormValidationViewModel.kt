package com.example.jetpack_compose_demo.ui.formValidation.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_demo.ui.formValidation.domain.use_case.ValidateEmail
import com.example.jetpack_compose_demo.ui.formValidation.domain.use_case.ValidateTerms
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormValidationViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validateTerms: ValidateTerms
) : ViewModel() {

    //    var state = mutableStateOf(RegistrationFormState())
    var state by mutableStateOf(RegistrationFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvent = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.EmailChange -> {
                state = state.copy(email = event.email)
            }

            is RegistrationFormEvent.AcceptTerms -> {
                state = state.copy(acceptTerms = event.isAccepted)
            }

            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val termsResult = validateTerms.execute(state.acceptTerms)

        val hasError = listOf(
            emailResult,
            termsResult
        ).any {
            !it.successful
        }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                termsError = termsResult.errorMessage
            )

            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}