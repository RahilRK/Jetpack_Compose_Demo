package com.example.jetpack_compose_demo.ui.categoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_demo.data.model.Category
import com.example.jetpack_compose_demo.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val repository: MyRepository,
) : ViewModel() {

    val categoryList: StateFlow<List<Category>>
        get() = repository.categoryList

    init {
        getCategoryList()
    }

    private fun getCategoryList() = viewModelScope.launch {
        repository.getCategoryList()
    }
}