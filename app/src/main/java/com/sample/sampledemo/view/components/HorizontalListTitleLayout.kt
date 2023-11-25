package com.sample.sampledemo.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.sample.sampledemo.R
import com.sample.sampledemo.utils.AppUtil.dpToSp

@Composable
fun HorizontalListTitleLayout(text: String) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        Modifier.wrapContentHeight()
        val (refTitleText, refViewAllText) = createRefs()
        Text(
            text = text,
            style = TextStyle(
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.W400,
                fontSize = dpToSp(14.dp),
            ),
            modifier = Modifier
                .padding(16.dp, 10.dp, 10.dp, 0.dp)
                .constrainAs(refTitleText) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
        Text(
            text = stringResource(R.string.view_all),
            style = TextStyle(
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.W400,
                fontSize = dpToSp(8.dp),
            ),
            modifier = Modifier
                .padding(16.dp, 10.dp, 10.dp, 0.dp)
                .constrainAs(refViewAllText) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}
