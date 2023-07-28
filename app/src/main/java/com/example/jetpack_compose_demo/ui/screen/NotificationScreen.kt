package com.example.jetpack_compose_demo.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val TAG = "NotificationScreen"

/*todo here we are setting value to state variable using
    lamba function and showing it in UI*/



@Composable
fun NotificationCounter(count: MutableState<Int>, increment: () -> Int) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "You have sent ${count.value} notifications.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Button(onClick = {
            increment()
            Log.d(TAG, "onClick: ${count.value}")
        }, Modifier.padding(top = 4.dp)) {
            Text(text = "Send Notification", fontSize = 16.sp)
        }
    }
}

@Composable
fun NotificationMessageCard(count: MutableState<Int>) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        ), modifier = Modifier.padding(top = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Current notifications: ${count.value}.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
    }
}
