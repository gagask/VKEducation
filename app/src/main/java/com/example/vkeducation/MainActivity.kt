package com.example.vkeducation

import android.graphics.drawable.PaintDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.vkeducation.ui.theme.VKeducationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKeducationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RuStoreApps(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RuStoreApps(modifier: Modifier = Modifier) {

    Column {
        RuStoreAppsToolbar()
        LazyColumn {}
    }
}

@Composable
fun RuStoreAppsToolbar(){
    Row(){
        Icon(
            painter = painterResource(R.drawable.store_24px),
            contentDescription = "RuStore icon",
        )
        Text("RuStore")
        Spacer(
            modifier = Modifier.weight(1F)
        )
        Icon(
            painter = painterResource(R.drawable.menu_24px),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RuStoreAppsPreview() {
    VKeducationTheme {
        RuStoreApps()
    }
}