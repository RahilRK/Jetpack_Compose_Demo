package com.example.jetpack_compose_demo.ui.categoryList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpack_compose_demo.data.model.Category


@Composable
fun CategoryListScreen(onClick:(strCategory: String)-> Unit) {

    val viewModel: CategoryListViewModel = hiltViewModel()
    val categoryListState = viewModel.categoryList.collectAsState()

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(categoryListState.value) { model ->

            CategoryListItem(strCategory = model.strCategory, onClick = onClick)
        }
    }
}

@Composable
fun CategoryListItem(strCategory: String, onClick:(strCategory: String)-> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        ), modifier = Modifier.padding(4.dp).clickable {
            onClick(strCategory)
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(4.dp)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(4.dp)
                .size(160.dp)
        ) {
            Text(text = strCategory, fontSize = 24.sp)
        }
    }

}

