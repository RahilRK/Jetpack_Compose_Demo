package com.example.jetpack_compose_demo.ui.mealList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_demo.data.model.Meal
import com.example.jetpack_compose_demo.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealListViewModel @Inject constructor(
    private val repository: MyRepository,
) : ViewModel() {

    val mealList: StateFlow<List<Meal>>
        get() = repository.mealList

    fun getMealList(strCategory: String) {
        viewModelScope.launch {
            repository.getMealList(strCategory)
        }
    }
}