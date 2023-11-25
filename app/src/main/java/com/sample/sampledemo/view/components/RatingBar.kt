package com.sample.sampledemo.view.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sample.sampledemo.R
import com.sample.sampledemo.utils.AppUtil.loadImageFromDrawable

@Composable
fun RatingBar(count: Int) {
    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .height(6.dp)
    ) {
        item {
            loadImageFromDrawable(R.drawable.ic_star_in_active).value?.let { bitmap ->
                RatingImageView(bitmap, isActive = count >= 1)
                Spacer(modifier = Modifier.width(5.dp))
                RatingImageView(bitmap, isActive = count >= 2)
                Spacer(modifier = Modifier.width(5.dp))
                RatingImageView(bitmap, isActive = count >= 3)
                Spacer(modifier = Modifier.width(5.dp))
                RatingImageView(bitmap, isActive = count >= 4)
                Spacer(modifier = Modifier.width(5.dp))
                RatingImageView(bitmap, isActive = count >= 5)
            }
        }
    }
}