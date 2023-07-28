package com.example.jetpack_compose_demo.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpack_compose_demo.R
import com.example.jetpack_compose_demo.ui.screen.ListItem
import com.example.jetpack_compose_demo.ui.screen.NotificationCounter
import com.example.jetpack_compose_demo.ui.screen.NotificationMessageCard
import com.example.jetpack_compose_demo.ui.screen.TAG
import com.example.jetpack_compose_demo.ui.screen.getEmployeeList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            CircleImageC()
//            LazyListC()
//            NotificationScreen()
//            LaunchedEffect()
//            Scope()
//            DisposableEffect()
//            ProduceState()
//            DerivedStateOf()
//            CategoryListScreen()
//            MealListScreen()
            App()
        }
    }
}


@Composable
fun App() {
    /*implementing Navigation framework*/
    val navController = rememberNavController() // declare Nav Controller
    NavHost(navController = navController, startDestination = "screen one") {
        //define NavGraph
        //add Screen One node
        composable(route = "screen one") {
            ScreenOne(navController)// 1st way to navigate using simple para
        }
        //add Screen Two node
        composable(route = "screen two") {
            ScreenTwo { // 2nd way to navigate by passing lambda function as para
                navController.navigate("screen three/${it}")
            }
        }
        //add Screen Three node
        composable(route = "screen three/{name}", arguments = listOf(
            navArgument("name") {
                type = NavType.StringType
            }
        )) {
            val getNameArgs = it.arguments!!.getString("name")
            ScreenThree(getNameArgs)
        }
    }
}

@Composable
fun ScreenOne(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Screen One",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp
        )
        Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
            navController.navigate("screen two")
        }) {
            Text(text = "Click here", fontSize = 16.sp)
        }
    }
}

@Composable
fun ScreenTwo(navigate: (name: String)-> Unit) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Screen Two",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp
        )
        Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
            navigate("Rahil RK")
        }) {
            Text(text = "Click here", fontSize = 16.sp)
        }
    }
}

@Composable
fun ScreenThree(getNameArgs: String?) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Screen Three: $getNameArgs",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp
        )
        Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = { /*TODO*/ }) {
            Text(text = "Click here", fontSize = 16.sp)
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

@Preview
@Composable
fun LaunchedEffect() {

    val count = remember { mutableStateOf(0) }
    LaunchedEffect(key1 = Unit) {
        Log.d("LaunchedEffect", "Started count: ${count.value}")
        for (i in 1..10) {
            delay(1000)
            count.value++
        }
        Log.d("LaunchedEffect", "Stopped count: ${count.value}")
    }


    Text(text = "Counter: ${count.value}", fontSize = 16.sp)
}

@Preview
@Composable
fun Scope() {
    val count = rememberSaveable { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    var text = "Counter is running ${count.value}"
    if (count.value == 10) {
        text = "Counter stopped"
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, modifier = Modifier.padding(bottom = 4.dp))
        Button(onClick = {

            scope.launch {
                Log.d(TAG, "Scope: Started")
                for (i in 1..10) {
                    count.value++
                    delay(1000)
                }
            }
        }) {
            Text(text = "Done")
        }
    }
}

@Preview
@Composable
fun DisposableEffect() {
    val count = remember { mutableStateOf(false) }

    androidx.compose.runtime.DisposableEffect(key1 = count.value) {

        Log.d(TAG, "DisposableEffect: Started")

        onDispose {
            Log.d(TAG, "DisposableEffect: Cleaning up side effects")
        }
    }

    Button(onClick = { count.value = !count.value }) {
        Text(text = "Change State")
    }
}

@Preview
@Composable
fun ProduceState() {

    val count = produceState(initialValue = 0) {
        Log.d("ProduceState", "Started count: $value")
        for (i in 1..10) {
            delay(1000)
            value += 1
        }
        Log.d("ProduceState", "Stopped count: $value")
    }

    Text(text = "Counter: ${count.value}", fontSize = 16.sp)
}

@Preview
@Composable
fun DerivedStateOf() {

    val tableOf = remember { mutableStateOf(5) }
//    val index = remember { mutableStateOf(1) }
    val index = produceState(initialValue = 1) {

        repeat(9) {
            delay(1000)
            value += 1
        }
    }

    val result = remember {
        derivedStateOf {
            "${tableOf.value} * ${index.value} = ${tableOf.value * index.value}"
        }
    }

    Text(text = result.value, fontSize = 16.sp)
}

