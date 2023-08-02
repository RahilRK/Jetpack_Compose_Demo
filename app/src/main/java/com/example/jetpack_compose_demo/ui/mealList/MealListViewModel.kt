package com.example.jetpack_compose_demo.ui.mealList

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_demo.data.model.Meal
import com.example.jetpack_compose_demo.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class MealListViewModel @Inject constructor(
    private val repository: MyRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    init {
        getMealList()
    }

    val mealList: StateFlow<List<Meal>>
        get() = repository.mealList

    private fun getMealList() {
        val strCategory = savedStateHandle.get<String>("strCategory") ?: "Beef"
        viewModelScope.launch {
            repository.getMealList(strCategory)
        }
    }
}