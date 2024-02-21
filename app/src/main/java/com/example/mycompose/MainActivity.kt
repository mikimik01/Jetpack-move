package com.example.mycompose

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycompose.ui.theme.MyComposeTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    var ctx:Context = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTheme {
                Greeting()
            }
        }
    }
}




@SuppressLint("UnrememberedMutableState")
@Composable
fun Greeting() {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    var move by remember{
        mutableStateOf(false)
    }

    var left by remember{
        mutableStateOf(0)
    }

    var top by remember{
        mutableStateOf(0)
    }

    var sign1 by remember{
        mutableStateOf(1)
    }
    var sign2 by remember{
        mutableStateOf(1)
    }

    val iconSize = 60.dp






    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        Icon(
            imageVector = Icons.Default.MailOutline,
            contentDescription = null,
            modifier = Modifier
                .size(iconSize)
                .offset(x = left.dp, y = top.dp)
        )
        val velocisty = 5
        if(left.dp>=screenWidth-iconSize || left.dp<0.dp) sign1=-sign1
        if(top>0 || -top.dp>screenHeight-iconSize) sign2=-sign2

        if(move) left+=velocisty*sign1
        if(move) top+=velocisty*sign2
    }


    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
        ){

            Button(
                onClick = {
                    move = false
                },
            ) {
                Text(screenHeight.toString())
            }

            Button(onClick = {
                move = true
            }) {
                Text(top.toString())
            }

            Button(onClick = {
                move = false
                left = 0
                top = 0
            }) {
                Text("Reset")
            }


        }

    }







}




/*
@Preview(
    showBackground = true,
        device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun GreetingPreview() {
    MyComposeTheme {
        Greeting()
    }
}*/
