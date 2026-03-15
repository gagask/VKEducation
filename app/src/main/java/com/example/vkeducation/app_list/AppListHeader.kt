package com.example.vkeducation.app_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkeducation.R

@Composable
fun AppListHeader(
    onLogoClicked: () -> Unit = {}
){
    Row(
        modifier = Modifier
            .padding(vertical = 24.dp)
            .padding(horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            painter = painterResource(R.drawable.store_24px),
            contentDescription = "RuStore icon",
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
                .clickable{
                    onLogoClicked()
                }
        )
        Text(
            "RuStore",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
        )
        Spacer(
            modifier = Modifier.weight(1F)
        )
        Icon(
            painter = painterResource(R.drawable.menu_24px),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(40.dp)
        )
    }
}