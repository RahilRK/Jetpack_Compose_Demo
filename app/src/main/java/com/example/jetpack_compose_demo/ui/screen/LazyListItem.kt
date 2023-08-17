package com.example.jetpack_compose_demo.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_demo.R


/*todo here we are creating LazyList like Recycler view and it's item with it's array data and model class*/

@Preview
@Composable
fun PreviewListItem() {

    ListItem(resID = R.drawable.mom, name = "Rahil", designation = "Developer")
}

@Composable
fun ListItem(
    resID: Int,
    name: String,
    designation: String,
    onItemClick: (model: Employee) -> Unit = {}
) {

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
            Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = resID), contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
//                    .weight(.2f)
                    .size(60.dp)
                    .clickable {
                        onItemClick(Employee(resId = resID, name = name, designation = designation))
                    }
                    .padding(8.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.LightGray, CircleShape),
            )
            ItemDescription(name, designation, Modifier.weight(.8f))

        }
    }
}

@Composable
private fun ItemDescription(name: String, designation: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium,
            fontSize = 16.sp
        )

        Text(
            text = designation,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Thin,
            fontSize = 16.sp
        )
    }
}

data class Employee(
    val resId: Int = R.drawable.mom,
    val name: String = "",
    val designation: String = "",
)

fun getEmployeeList(): MutableList<Employee> {

    val list = mutableListOf<Employee>()
    list.add(Employee(name = "Rahil", designation = "Developer"))
    list.add(Employee(name = "Sameer", designation = "Engineer"))
    list.add(Employee(name = "Zahir", designation = "Artist"))
    list.add(Employee(name = "Seema", designation = "Doctor"))
    list.add(Employee(name = "Rahil", designation = "Developer"))
    list.add(Employee(name = "Sameer", designation = "Engineer"))
    list.add(Employee(name = "Zahir", designation = "Artist"))
    list.add(Employee(name = "Seema", designation = "Doctor"))
    list.add(Employee(name = "Rahil", designation = "Developer"))
    list.add(Employee(name = "Sameer", designation = "Engineer"))
    list.add(Employee(name = "Zahir", designation = "Artist"))
    list.add(Employee(name = "Seema", designation = "Doctor"))
    list.add(Employee(name = "Rahil", designation = "Developer"))
    list.add(Employee(name = "Sameer", designation = "Engineer"))
    list.add(Employee(name = "Zahir", designation = "Artist"))
    list.add(Employee(name = "Seema", designation = "Doctor"))
    list.add(Employee(name = "End", designation = "Done"))

    return list
}