package com.example.jetpack_compose_demo.ui.searchview

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchViewScreenC() {

    val viewModel: SearchViewViewModel = hiltViewModel()
    val searchViewState by viewModel.searchViewState
    val searchTextState by viewModel.searchTextState

    Scaffold(
        topBar = {
            /*DefaultAppBar(onSearchClicked = {
                Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
            })*/
            MainAppBar(
                searchViewState = searchViewState,
                searchText = searchTextState,
                onTextChange = {
                    viewModel.updateSearchTextState(newValue = it)
                },
                onCloseClick = {
                    viewModel.updateSearchTextState(newValue = "")
                    viewModel.updateSearchViewState(newValue = SearchViewState.CLOSED)
                },
                onSearchClick = {

                    Log.d("TAG", "SearchViewScreenC: $it")
                },
                onSearchTriggered = {
                    viewModel.updateSearchViewState(newValue = SearchViewState.OPENED)
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {

        }
    }
}

@Composable
fun MainAppBar(
    searchViewState: SearchViewState,
    searchText: String,
    onTextChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSearchClick: (String) -> Unit,
    onSearchTriggered: () -> Unit,
) {
    if (searchViewState == SearchViewState.CLOSED) {
        DefaultAppBar(onSearchClicked = onSearchTriggered)
    } else if (searchViewState == SearchViewState.OPENED) {
        SearchAppBar(
            text = searchText,
            onTextChange = onTextChange,
            onCloseClick = onCloseClick,
            onSearchClick = onSearchClick
        )
    }
}

@Composable
fun DefaultAppBar(onSearchClicked: () -> Unit) {
    TopAppBar(title = {
        Text(text = "Home", color = Color.White)
    }, actions = {
        IconButton(onClick = {
            onSearchClicked()
        }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = Color.White
            )
        }
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
    )
    )
}

@Preview
@Composable
fun DefaultAppBarPreview() {
    DefaultAppBar(onSearchClicked = {

    })
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    onSearchClick: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        color = MaterialTheme.colorScheme.primary,
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(text = "Search here", color = Color.White.copy(alpha = 0.5f))
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                color = Color.White
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "",
                        tint = Color.White.copy(alpha = 0.5f)
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) {
                        onTextChange("")
                    } else {
                        onCloseClick()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClick(text)
                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = 0.5f),
            )
        )
    }
}

@Preview
@Composable
fun SearchAppBarPreview() {
    SearchAppBar(
        text = "",
        {},
        {},
        {}
    )
}