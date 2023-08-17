package com.example.jetpack_compose_demo.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpack_compose_demo.R
import com.example.jetpack_compose_demo.data.model.ToggleData
import com.example.jetpack_compose_demo.ui.categoryList.CategoryListScreen
import com.example.jetpack_compose_demo.ui.mealList.MealListScreen
import com.example.jetpack_compose_demo.ui.navigationBottomBar.NavigationBottomBar
import com.example.jetpack_compose_demo.ui.navigationDrawer.NavigationDrawer
import com.example.jetpack_compose_demo.ui.screen.ListItem
import com.example.jetpack_compose_demo.ui.screen.NotificationCounter
import com.example.jetpack_compose_demo.ui.screen.NotificationMessageCard
import com.example.jetpack_compose_demo.ui.screen.TAG
import com.example.jetpack_compose_demo.ui.screen.getEmployeeList
import com.example.jetpack_compose_demo.ui.searchview.SearchViewScreenC
import com.example.jetpack_compose_demo.ui.theme.Jetpack_Compose_DemoTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            TextFieldC()
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
//            App()
//            CategoryApp()
//            CheckBoxC()
//            PreviewCheckBoxes()
            SearchViewScreenC()
        }
    }
}

@Composable
fun CategoryApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "categoryListScreen") {
        composable(route = "categoryListScreen") {
            CategoryListScreen(onClick = { strCategory ->
                navController.navigate("mealListScreen/$strCategory")
            })
        }
        composable(route = "mealListScreen/{strCategory}", arguments = listOf(
            navArgument("strCategory") {
                type = NavType.StringType
            }
        )) {
            MealListScreen()
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
fun ScreenTwo(navigate: (name: String) -> Unit) {

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
        color = Color.Red,
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

    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {
                Toast.makeText(context, "Button is clicked", Toast.LENGTH_SHORT).show()
            },
            /*colors = ButtonDefaults.buttonColors(
                           containerColor = Color.Blue,
                           contentColor = Color.White,
                       ),*/
//            shape = RoundedCornerShape(4.dp),
        ) {
            Text(text = "Create a new account")
        }


        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = {
                Toast.makeText(context, "Button is clicked", Toast.LENGTH_SHORT).show()
            },
        ) {
            Text(text = "I have an existing account")
        }

        Spacer(modifier = Modifier.height(16.dp))

        ElevatedButton(
            onClick = {
                Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()
            },
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "",
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Add to cart")
        }

        Spacer(modifier = Modifier.height(16.dp))

        FilledTonalButton(
            onClick = {
                Toast.makeText(context, "Button is clicked", Toast.LENGTH_SHORT).show()
            },
        ) {
            Text(text = "Open in browser")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {
                Toast.makeText(context, "Button is clicked", Toast.LENGTH_SHORT).show()
            },
        ) {
            Text(text = "Learn more")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextFieldC() {

    val getValue = rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = getValue.value,
            /*readOnly = true,*/
            onValueChange = {
                getValue.value = it
            },
            /*label = { Text(text = "Enter your name") },*/
            placeholder = {
                Text(text = "Name")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "")
            },
            trailingIcon = {
                if (getValue.value.isNotBlank()) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "",
                        modifier = Modifier.clickable {

                            getValue.value = ""
                        })
                }
            },
            /*prefix = {
                Text(text = "+91")
            }*/
            /*supportingText = {
                Text(text = "Invalid name")
            },
            isError = true*/
//            visualTransformation = PasswordVisualTransformation()
            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    Toast.makeText(context, "Done is clicked", Toast.LENGTH_SHORT).show()
                }
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = getValue.value,
            /*readOnly = true,*/
            onValueChange = {
                getValue.value = it
            },
            /*label = { Text(text = "Enter your name") },*/
            placeholder = {
                Text(text = "Name")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "")
            },
            trailingIcon = {
                if (getValue.value.isNotBlank()) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "",
                        modifier = Modifier.clickable {

                            getValue.value = ""
                        })
                }
            },
            /*prefix = {
                Text(text = "+91")
            }*/
            /*supportingText = {
                Text(text = "Invalid name")
            },
            isError = true*/
//            visualTransformation = PasswordVisualTransformation()
            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    Toast.makeText(context, "Done is clicked", Toast.LENGTH_SHORT).show()
                }
            )
        )

    }
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

    val context = LocalContext.current
    val list = getEmployeeList()

    LazyColumn(content = {
        items(list) { model ->

            /*val mState = rememberDismissState(
                confirmValueChange = {
                    if (it == DismissValue.DismissedToEnd) {
                        list.remove(model)
                    }
                    true
                }
            )
            SwipeToDismiss(state = mState, background = {
                val color =
                    if (mState.dismissDirection == DismissDirection.StartToEnd) {
                        Color.Transparent
                    } else if (mState.dismissDirection == DismissDirection.EndToStart) {
                        Color.Red
                    } else {
                        Color.Transparent
                    }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = color)
                        .padding(10.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterEnd),
                        imageVector = Icons.Outlined.DeleteOutline,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }
            }, dismissContent = {
                ListItem(
                    resID = model.resId,
                    name = model.name,
                    designation = model.designation,
                    onItemClick = { model ->
                        list.remove(model)
                        Toast.makeText(context, model.name, Toast.LENGTH_SHORT).show()
                    })
            }, directions = setOf(DismissDirection.EndToStart))*/

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

@Preview
@Composable
fun CheckBoxC() {

    val mainActivityViewModel: MainActivityViewModel = viewModel()
    val getCheckedValue by mainActivityViewModel.checkBox_isChecked.collectAsState()

    Row(verticalAlignment = Alignment.CenterVertically) {

        Checkbox(checked = getCheckedValue, onCheckedChange = {

            mainActivityViewModel.checkBoxEvent(it)

        }, colors = CheckboxDefaults.colors(uncheckedColor = Color.Blue, checkedColor = Color.Blue))
        Text(
            text = "Check me",
            color = Color.Blue,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun CheckBoxList() {

    val checkboxes = remember {
        mutableStateListOf(
            ToggleData(title = "Cricket", isChecked = false),
            ToggleData(title = "Volleyball", isChecked = false),
            ToggleData(title = "Video game", isChecked = false)
        )
    }

    checkboxes.forEachIndexed { index, model ->
        Row(verticalAlignment = Alignment.CenterVertically) {

            Checkbox(
                checked = model.isChecked,
                onCheckedChange = { isChecked ->
                    checkboxes[index] = model.copy(
                        isChecked = isChecked
                    )
                },
                colors = CheckboxDefaults.colors(
                    uncheckedColor = Color.Blue,
                    checkedColor = Color.Blue
                )
            )
            Text(
                text = model.title,
                color = Color.Blue,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}

@Preview
@Composable
fun PreviewCheckBoxes() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Text(text = "Choose your hobbies", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        CheckBoxList()
    }
}

@Preview
@Composable
fun Switch() {
    Row(verticalAlignment = Alignment.CenterVertically) {

        val mainActivityViewModel: MainActivityViewModel = viewModel()
        val getCheckedValue by mainActivityViewModel.switch_isChecked.collectAsState()

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = getCheckedValue.title,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.weight(1f))
        androidx.compose.material3.Switch(
            modifier = Modifier.padding(end = 8.dp),
            checked = getCheckedValue.isChecked,
            onCheckedChange = {

                if (it) {
                    mainActivityViewModel.switchEvent(ToggleData(title = "Light Mode", true))
                } else {
                    mainActivityViewModel.switchEvent(ToggleData(title = "Dark Mode", false))
                }

            },
        )
    }
}

@Composable
fun RadioButtonList() {

    val radioButtons = remember {
        mutableStateListOf(
            ToggleData(title = "Cricket", isChecked = true),
            ToggleData(title = "Volleyball", isChecked = false),
            ToggleData(title = "Video game", isChecked = false)
        )
    }

    radioButtons.forEachIndexed { index, model ->
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {

                radioButtons.replaceAll {
                    it.copy(
                        isChecked = it.title == model.title
                    )
                }
            }) {

            RadioButton(
                selected = model.isChecked,
                onClick = {
                    radioButtons.replaceAll {
                        it.copy(
                            isChecked = it.title == model.title
                        )
                    }
                },
            )
            Text(
                text = model.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
fun PreviewRadioButton() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Text(text = "Select your hobbies", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        RadioButtonList()
    }
}

@Preview(showBackground = true)
@Composable
fun NavDrawerPreview() {
    Jetpack_Compose_DemoTheme {
        Surface(content = {
            NavigationDrawer()
        })
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationBottomBarPreview() {
    Jetpack_Compose_DemoTheme {
        NavigationBottomBar()
    }
}

