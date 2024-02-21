package com.example.mycompose

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycompose.ui.theme.MyComposeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    var ctx:Context = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTheme {
                Greeting()
                //Cube()
            }
        }
    }
}




@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun Greeting() {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    var move by remember{mutableStateOf(false)}
    var left by remember{mutableStateOf(0)}
    var top by remember{mutableStateOf(0)}
    var sign1 by remember{mutableStateOf(1)}
    var sign2 by remember{mutableStateOf(1)}
    var signRot by remember{mutableStateOf(1)}
    var rotate by remember{mutableStateOf(0f)}
    var colisions by remember{mutableStateOf(0)}
    var iconn by remember{mutableStateOf(Icons.Default.MailOutline)}
    val icList = listOf(Icons.Default.MailOutline, Icons.Default.AccountBox, Icons.Default.AccountCircle,
        Icons.Default.Build, Icons.Default.Delete, Icons.Default.Lock, Icons.Default.ShoppingCart)

    val iconSize = 60.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .combinedClickable (
                onClick = {
                    move=!move
                },
                onLongClick = {
                    move = false
                    left = 0
                    top = 0
                    rotate = 0f
                    iconn = Icons.Default.MailOutline
                    sign1=1
                    sign2=1
                    signRot=1
                }
            ),
        contentAlignment = Alignment.BottomStart
    ) {
        Icon(
            imageVector = iconn,
            contentDescription = null,
            modifier = Modifier
                .size(iconSize)
                .offset(x = left.dp, y = top.dp)
                .rotate(rotate)
        )
        val velocity = 5
        val rotSpeed = 5

        if(left.dp>=screenWidth-iconSize || left.dp<0.dp) {
            sign1=-sign1
            signRot=-signRot
            colisions++
        }

        if(top>0 || -top.dp>screenHeight-iconSize) {
            sign2=-sign2
            signRot=-signRot
            colisions++
        }

        if (colisions>=2){
            colisions=0
            iconn = icList[Random.nextInt(0, icList.size-1)]
        }

        if(move) {
            left+=velocity*sign1
            top+=velocity*sign2
            rotate+=rotSpeed*signRot
        }

    }
}


@Composable
fun Cube() {
    // Draw the front face of the cube
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    )
    {
        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer {
                    rotationX = 45f
                    rotationZ = 45f
                    rotationY = 45f
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue)
            )

            // Draw the right face of the cube
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green)
                    .graphicsLayer(rotationZ = 90f)
            )

            // Draw the top face of the cube
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
                    .graphicsLayer(rotationX = 90f)
            )
        }
    }
}




@Preview(
    showBackground = true,
        device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun GreetingPreview() {
    MyComposeTheme {
        Greeting()
        //Cube()
    }
}
