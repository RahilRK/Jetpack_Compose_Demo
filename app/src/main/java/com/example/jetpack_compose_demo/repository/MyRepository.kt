package com.example.jetpack_compose_demo.repository

import com.example.jetpack_compose_demo.data.model.Category
import com.example.jetpack_compose_demo.data.model.Meal
import com.example.jetpack_compose_demo.data.remote.ApiInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MyRepository @Inject constructor(
    private val apiInterface: ApiInterface,
) {

    private val _categoryList = MutableStateFlow<List<Category>>(emptyList())
    val categoryList: StateFlow<List<Category>>
        get() = _categoryList

    private val _mealList = MutableStateFlow<List<Meal>>(emptyList())
    val mealList: StateFlow<List<Meal>>
        get() = _mealList

    suspend fun getCategoryList() {

        val response = apiInterface.getCategoryList()
        if (response.isSuccessful) {
            response.body()?.let {
                _categoryList.emit(it.categories)
            }
        }
    }

    suspend fun getMealList(strCategory: String) {

        val response = apiInterface.getMealList(strCategory)
        if (response.isSuccessful) {
            response.body()?.let {
                _mealList.emit(it.meals)
            }
        }
    }
}