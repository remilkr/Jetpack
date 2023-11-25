package com.sample.sampledemo.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sample.sampledemo.R
import com.sample.sampledemo.utils.AppUtil.dpToSp

@Composable
fun DiscountText(text: String) {
    val bgColor = if (text.isBlank()) {
        android.R.color.transparent
    } else {
        R.color.color_discount_label_bg
    }
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                Color(
                    LocalContext.current.getColor(bgColor)
                ), RoundedCornerShape(20)
            )
    )
    {
        Text(
            text = text,
            style = TextStyle(
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W400,
                fontSize = dpToSp(5.dp),
            ),
            modifier = Modifier
                .padding(6.dp),
        )
    }
}