package com.lesa.burunduk.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.lesa.burunduk.R

data class LeSaColors(
    val base: Color,
    val primary: Color,
    val text: Color,
    val background90: Color,
    val background80: Color,
    val error: Color
)

data class LeSaModes(
    val isDarkMode: Boolean
)

data class LeSaTypography(
    val hat: TextStyle,
    val head: TextStyle,
    val body: TextStyle
)

data class LeSaShape(
    val padding: Dp
    //val cornerStyle: Shape
)

object LeSaTheme {
    val colors: LeSaColors
        @Composable
        get() = LocalLeSaColors.current

    val mode: LeSaModes
        @Composable
        get() = LocalLeSaModes.current

    val typography: LeSaTypography
        @Composable
        get() = LocalLeSaTypography.current

    val shape: LeSaShape
        @Composable
        get() = LocalLeSaShape.current
}

enum class LeSaColorStyle(val darkColor: Color, val lightColor: Color) {
    Green(
        greenDarkPalette.primary,
        greenLightPalette.primary
    ),
    Yellow(
        yellowDarkPalette.primary,
        yellowLightPalette.primary
    ),
    Orange(
        orangeDarkPalette.primary,
        orangeLightPalette.primary
    ),
    Red(
        redDarkPalette.primary,
        redLightPalette.primary
    ),
    Purple(
        purpleDarkPalette.primary,
        purpleLightPalette.primary
    ),
    Blue(
        blueDarkPalette.primary,
        blueLightPalette.primary
    ),
    Base(
        baseDarkPalette.primary,
        baseLightPalette.primary
    )
}

enum class LeSaModeStyle(val description: Int, val icon: Int) {
    LightMode(
        R.string.light_mode,
        R.drawable.light_mode_filled
    ),
    DarkMode(
        R.string.dark_mode,
        R.drawable.dark_mode_filled
    ),
    SystemMode(
        R.string.system_mode,
        R.drawable.phonelink_setup
    )
}
enum class LeSaTextSize {
    Small, Medium, Big
}

enum class LeSaPadding {
    Small, Medium, Big
}

val LocalLeSaColors = staticCompositionLocalOf<LeSaColors> {
    error("No colors provided")
}

val LocalLeSaModes = staticCompositionLocalOf<LeSaModes> {
    error("No modes provided")
}

val LocalLeSaTypography = staticCompositionLocalOf<LeSaTypography> {
    error("No typography provided")
}

val LocalLeSaShape = staticCompositionLocalOf<LeSaShape> {
    error("No shape provided")
}