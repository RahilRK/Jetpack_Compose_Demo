package com.example.jetpack_compose_demo.ui.searchview

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchViewViewModel : ViewModel() {

    private val _searchViewState: MutableState<SearchViewState> = mutableStateOf(value = SearchViewState.CLOSED)
    val searchViewState: State<SearchViewState> = _searchViewState

    private val _searchTextState = mutableStateOf(value = "")
    val searchTextState = _searchTextState

    fun updateSearchViewState(newValue: SearchViewState) {
        _searchViewState.value = newValue
    }
    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }
}