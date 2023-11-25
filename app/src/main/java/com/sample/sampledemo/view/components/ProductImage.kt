package com.sample.sampledemo.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import com.sample.sampledemo.utils.AppUtil.loadImage

@Composable
fun ProductImage(imageUrl: String) {
    loadImage(url = imageUrl).value?.let { bitmap ->
        Image(
            modifier = Modifier
                .fillMaxSize(),
            bitmap = bitmap.asImageBitmap(),
            contentScale = ContentScale.FillBounds,
            contentDescription = ""
        )
    }
}
