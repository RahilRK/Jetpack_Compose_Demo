package com.example.jetpack_compose_demo.ui.formValidation.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FormValidationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Form()
        }
    }
}

@Preview
@Composable
fun Form() {

    val formValidationViewModel: FormValidationViewModel = viewModel()
    val state = formValidationViewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        formValidationViewModel.validationEvent.collect { event ->
            if (event == FormValidationViewModel.ValidationEvent.Success) {
                Toast.makeText(context, "Registered successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            TextField(value = state.email,
                singleLine = true,
                onValueChange = {
                    formValidationViewModel.onEvent(RegistrationFormEvent.EmailChange(it))
                },
                isError = state.emailError != null,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                label = { Text(text = "Enter email") })

            if (state.emailError != null) {
                Text(
                    text = state.emailError,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Checkbox(checked = state.acceptTerms, onCheckedChange = {
                    formValidationViewModel.onEvent(RegistrationFormEvent.AcceptTerms(it))

                })

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Accept Terms and conditions",
                )
            }

            if (state.termsError != null) {
                Text(
                    text = state.termsError,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Button(modifier = Modifier.align(Alignment.CenterHorizontally),onClick = {
                formValidationViewModel.onEvent(RegistrationFormEvent.Submit)
            }) {
                Text(text = "Submit")
            }
        }
    }
}


