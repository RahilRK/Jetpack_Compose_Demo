package com.example.jetpack_compose_demo.ui

import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_demo.data.model.ToggleData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
//    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _checkBox_isChecked = MutableStateFlow(false)
    val checkBox_isChecked = _checkBox_isChecked.asStateFlow()
//    val checkBox_isChecked = savedStateHandle.getStateFlow("isCheckedValue", false)

    fun checkBoxEvent(value: Boolean) {
        _checkBox_isChecked.value = value
//        savedStateHandle["isCheckedValue"] = value
    }

    private val _switch_isChecked = MutableStateFlow(ToggleData(title = "Dark Mode", isChecked = false))
    val switch_isChecked = _switch_isChecked.asStateFlow()

    fun switchEvent(value: ToggleData) {
        _switch_isChecked.value = value
    }

}