package com.lesa.burunduk.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.lesa.burunduk.ui.theme.BlackBlue
import com.lesa.burunduk.ui.theme.Red
import com.lesa.burunduk.ui.theme.burundukFontFamily

@Composable
fun MyText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    color: Color = BlackBlue
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        textAlign = textAlign,
        maxLines = maxLines,
        color = color,
        overflow = overflow,
        fontFamily = burundukFontFamily,

    )
}

@Composable
fun MyTextBold(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = 1
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        textAlign = textAlign,
        maxLines = maxLines,
        color = BlackBlue,
        fontFamily = burundukFontFamily,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MyHeadline(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Text(
        text = text.uppercase(),
        modifier = modifier,
        fontSize = fontSize,
        textAlign = textAlign,
        fontWeight = fontWeight,
        color = Red,
        fontFamily = burundukFontFamily
    )
}