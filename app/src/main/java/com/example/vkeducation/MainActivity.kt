package com.example.vkeducation

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.vkeducation.ui.theme.VKeducationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKeducationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras

        setContent {
            Text(
                bundle?.getString("KEY_STRING", "Не передан текст") ?: "Ваще бред",
                modifier = Modifier.fillMaxSize().wrapContentHeight(align = Alignment.CenterVertically),
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    var text by remember { mutableStateOf("") }


    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = { text = it }
        )
        Button(
            onClick = {
                val intent = Intent(context, SecondActivity::class.java)
                intent.putExtra("KEY_STRING", text)
                context.startActivity(intent)
            }
        ) {
            Text(
                text = "Открыть вторую Activity"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VKeducationTheme {
        Greeting("Android")
    }
}