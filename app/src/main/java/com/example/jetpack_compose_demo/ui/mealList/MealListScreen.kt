package com.example.jetpack_compose_demo.ui.mealList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.jetpack_compose_demo.R
import com.example.jetpack_compose_demo.data.model.Meal


@Composable
fun MealListScreen() {

    val viewModel: MealListViewModel = hiltViewModel()
    val mealListState = viewModel.mealList.collectAsState()
    LazyColumn(content = {
        items(mealListState.value) { model ->
            MealListItem(model)
        }
    }, modifier = Modifier.padding(4.dp))

}

@Composable
fun MealListItem(model: Meal) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        ), modifier = Modifier.padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(4.dp)
    ) {

        Row(
            Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
//                painterResource(id = R.drawable.ic_profile), contentDescription = "",
                model = model.strMealThumb,
                modifier = Modifier.size(80.dp),
                contentDescription = "Loading image from url"
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
//                    .background(Color.Green)
                    .padding(start = 4.dp)
            ) {
                Text(
                    text = model.strMeal,
                    fontSize = 20.sp,
                )
            }

        }
    }

}

