package com.example.kayaksonar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KayakSonarScreen()
        }
    }
}

@Composable
fun KayakSonarScreen() {

    var depth by remember { mutableStateOf(18.7) }
    var fishDepth by remember { mutableStateOf(listOf(5.0, 11.0)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "🎣 KayakSonar",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "${"%.1f".format(depth)} ft",
            fontSize = 45.sp
        )

        Text("Depth")

        Spacer(modifier = Modifier.height(20.dp))


        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {

            // Bottom line
            drawLine(
                color = Color.White,
                start = Offset(0f, size.height - 40),
                end = Offset(size.width, size.height - 70),
                strokeWidth = 5f
            )


            // Fish targets
            fishDepth.forEachIndexed { index, fish ->

                val x = size.width * ((index + 1) / 3f)

                val y = (fish / depth).toFloat() *
                        (size.height - 80)

                drawCircle(
                    color = Color.Cyan,
                    radius = 12f,
                    center = Offset(x, y)
                )
            }
        }


        Spacer(modifier = Modifier.height(20.dp))


        Button(
            onClick = {

                depth = Random.nextDouble(
                    5.0,
                    60.0
                )

                fishDepth = listOf(
                    Random.nextDouble(2.0, depth),
                    Random.nextDouble(2.0, depth)
                )
            }
        ) {
            Text("Scan Water")
        }


        Spacer(modifier = Modifier.height(20.dp))


        Text("📡 Bluetooth: Not Connected")
        Text("🔋 Battery: 86%")

    }
}
