package com.lesa.burunduk.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LeSaTheme(
    colorStyle: LeSaColorStyle = LeSaColorStyle.Red,
    modeStyle: LeSaModeStyle = LeSaModeStyle.SystemMode,
    //isDarkTheme: Boolean = isSystemInDarkTheme(),
    textSize: LeSaTextSize = LeSaTextSize.Medium,
    padding: LeSaPadding = LeSaPadding.Medium,
    content: @Composable () -> Unit
) {
    val mode = LeSaModes(
        when (modeStyle) {
            LeSaModeStyle.LightMode -> false
            LeSaModeStyle.DarkMode -> true
            LeSaModeStyle.SystemMode -> isSystemInDarkTheme()
        }
    )

    val colors = when (mode.isDarkMode) {
        true -> {
            when (colorStyle) {
                LeSaColorStyle.Base -> baseDarkPalette
                LeSaColorStyle.Yellow -> yellowDarkPalette
                LeSaColorStyle.Green -> greenDarkPalette
                LeSaColorStyle.Blue -> blueDarkPalette
                LeSaColorStyle.Purple -> purpleDarkPalette
                LeSaColorStyle.Red -> redDarkPalette
                LeSaColorStyle.Orange -> orangeDarkPalette
            }
        }
        false -> {
            when (colorStyle) {
                LeSaColorStyle.Base -> baseLightPalette
                LeSaColorStyle.Yellow -> yellowLightPalette
                LeSaColorStyle.Green -> greenLightPalette
                LeSaColorStyle.Blue -> blueLightPalette
                LeSaColorStyle.Purple -> purpleLightPalette
                LeSaColorStyle.Red -> redLightPalette
                LeSaColorStyle.Orange -> orangeLightPalette
            }
        }
    }

    val typography = LeSaTypography(
        hat = TextStyle(
            fontSize = when(textSize) {
                LeSaTextSize.Small -> 12.sp
                LeSaTextSize.Medium -> 16.sp
                LeSaTextSize.Big -> 20.sp
            },
            fontWeight = FontWeight.Normal
        ),
        head = TextStyle(
            fontSize = when(textSize) {
                LeSaTextSize.Small -> 10.sp
                LeSaTextSize.Medium -> 14.sp
                LeSaTextSize.Big -> 18.sp
            },
            fontWeight = FontWeight.Bold
        ),
        body = TextStyle(
            fontSize = when(textSize) {
                LeSaTextSize.Small -> 8.sp
                LeSaTextSize.Medium -> 12.sp
                LeSaTextSize.Big -> 16.sp
            },
            fontWeight = FontWeight.Normal
        )
    )

    val shape = LeSaShape(
        padding = when(padding) {
            LeSaPadding.Small -> 2.dp
            LeSaPadding.Medium -> 4.dp
            LeSaPadding.Big -> 8.dp
        }
    )

    CompositionLocalProvider(
        LocalLeSaColors provides colors,
        LocalLeSaModes provides mode,
        LocalLeSaTypography provides typography,
        LocalLeSaShape provides shape,
        content = content
    )
}

