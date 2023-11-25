package com.sample.sampledemo.view.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.sample.sampledemo.utils.AppUtil.dpToSp

@Composable
fun ProductPrice(productPrice: String?, offerPrice: String?) {
    LazyRow(
        modifier = Modifier
            .wrapContentSize()
    ) {
        item {
            val rateOfProduct = if (!offerPrice.isNullOrBlank() && offerPrice != productPrice) {
                offerPrice
            } else {
                productPrice
            }
            Text(
                text = "$rateOfProduct",
                style = TextStyle(
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W400,
                    fontSize = dpToSp(5.dp),
                )
            )
            if (!offerPrice.isNullOrBlank() && offerPrice != productPrice) {
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "$productPrice",
                    style = TextStyle(
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.W400,
                        textDecoration = TextDecoration.LineThrough,
                        fontSize = dpToSp(5.dp),
                    ),
                )
            }
        }
    }
}
