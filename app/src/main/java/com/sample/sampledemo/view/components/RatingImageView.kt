package com.sample.sampledemo.view.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sample.sampledemo.R

@Composable
fun RatingImageView(starImageBitmap: Bitmap, isActive: Boolean) {
    val highLightColorFilter = if (isActive) {
        ColorFilter.tint(Color(LocalContext.current.getColor(R.color.color_rating_star_active)))
    } else {
        null
    }
    Image(
        modifier = Modifier
            .height(5.dp)
            .width(5.dp),
        colorFilter = highLightColorFilter,
        bitmap = starImageBitmap.asImageBitmap(),
        contentScale = ContentScale.FillBounds,
        contentDescription = ""
    )
}