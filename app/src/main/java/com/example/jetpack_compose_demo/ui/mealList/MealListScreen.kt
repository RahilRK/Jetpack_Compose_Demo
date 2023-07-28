package com.example.jetpack_compose_demo.ui.mealList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpack_compose_demo.R
import com.example.jetpack_compose_demo.data.model.Meal


@Composable
fun MealListScreen() {

    val viewModel: MealListViewModel = viewModel()
    val mealListState = viewModel.mealList.collectAsState()

//    viewModel.getMealList("52944")

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

        Row(Modifier.padding(8.dp)) {
            Image(
                painterResource(id = R.drawable.ic_profile), contentDescription = "",
                Modifier.size(40.dp)
            )
            Column() {
                Text(
                    text = model.strMeal,
                    fontSize = 12.sp,
                )
            }

        }
    }

}

