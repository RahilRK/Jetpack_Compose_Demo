package com.example.jetpack_compose_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_demo.ui.ListItem
import com.example.jetpack_compose_demo.ui.getEmployeeList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationScreen()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextC(name: String = "Rahil") {
    Text(
        text = "Hello $name!",
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        color = androidx.compose.ui.graphics.Color.Red,
        fontSize = 24.sp,
//        textAlign = TextAlign.Right
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImageC() {
    Image(
        painter = painterResource(id = R.drawable.ic_add_certificate),
        contentDescription = "Dummy image",
        contentScale = ContentScale.Crop
//        colorFilter = ColorFilter.tint(Color.Red)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ButtonC() {

    Button(
        onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
            containerColor = androidx.compose.ui.graphics.Color.Red,
            contentColor = androidx.compose.ui.graphics.Color.White,
        )
    ) {
        Text(text = "Hello")
        Image(
            painter = painterResource(id = R.drawable.ic_add_certificate),
            contentDescription = "demo"
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextFieldC() {

    val getValue = remember { mutableStateOf("") }
    TextField(value = getValue.value,
        onValueChange = {
            getValue.value = it
        },
        label = { Text(text = "Enter name") })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ColumnC() {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "A",
            fontSize = 24.sp,
        )

        Text(
            text = "B",
            fontSize = 24.sp,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RowC() {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "A",
            fontSize = 24.sp,
        )

        Text(
            text = "B",
            fontSize = 24.sp,
        )
    }
}

@Composable
fun ListViewItemC(resID: Int, name: String, designation: String) {

    Row(Modifier.padding(8.dp)) {
        Image(
            painterResource(id = resID), contentDescription = "",
            Modifier.size(40.dp)
        )
        Column() {
            Text(
                text = name,
                fontSize = 12.sp,
            )

            Text(
                text = designation,
                fontSize = 12.sp,
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListC() {

    Column() {
        ListViewItemC(
            resID = R.drawable.ic_profile,
            name = "Rahil RK 1",
            designation = "Designation A"
        )
        ListViewItemC(
            resID = R.drawable.ic_profile,
            name = "Rahil RK 2",
            designation = "Designation B"
        )
        ListViewItemC(
            resID = R.drawable.ic_profile,
            name = "Rahil RK 3",
            designation = "Designation C"
        )
        ListViewItemC(
            resID = R.drawable.ic_profile,
            name = "Rahil RK 4",
            designation = "Designation D"
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, widthDp = 200, heightDp = 200)
@Composable
fun CircleImageC() {
    Image(
        painter = painterResource(id = R.drawable.mom),
        contentScale = ContentScale.Crop,
        contentDescription = "Demo Image",
        modifier = Modifier
            .size(124.dp)
            .padding(4.dp)
            .clip(CircleShape)
            .border(2.dp, androidx.compose.ui.graphics.Color.LightGray, CircleShape),
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LazyListC() {

    LazyColumn(content = {
        items(getEmployeeList()) { model ->
            ListItem(resID = model.resId, name = model.name, designation = model.designation)
        }
    }, modifier = Modifier.padding(4.dp))
}



@Preview
@Composable
/*todo here we are setting state data and showing it in ui*/
fun NotificationScreen() {

    val count = rememberSaveable { mutableStateOf(0) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(1f)
    ) {
        NotificationCounter(count) { count.value++ }
        NotificationMessageCard(count)
    }
}

