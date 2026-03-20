package com.example.vkeducation.app_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vkeducation.app_list.AppCard

@Composable
fun AppCard(app: AppCard, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{onClick()}
    ){
        Image(
            painter = painterResource(app.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(16.dp)),

            )
        Column {
            Text(
                text = app.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Text(app.shortDescription,
            )
            Text(
                text = app.type,
                fontSize = 12.sp,
            )
        }
    }
}

data class AppCard (
    val image: Int,
    val name: String,
    val shortDescription: String,
    val type: String
)