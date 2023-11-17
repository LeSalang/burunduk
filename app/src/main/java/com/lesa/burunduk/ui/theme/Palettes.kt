package com.lesa.burunduk.ui.theme

import androidx.compose.ui.graphics.Color

val baseLightPalette = LeSaColors(
    base = Color(0xFF7f7f7f),
    primary = Color(0xFF4c4c4c), //30%
    text = Color(0xFF0d0d0d), //90%
    background90 = Color(0xFFf2f2f2), //90%
    background80 = Color(0xFFe5e5e5),
    error = Color(0xFFD50000)
)

val baseDarkPalette = LeSaColors(
    base = Color(0xFF7f7f7f),
    primary = Color(0xFFb2b2b2),
    text = Color(0xFFf2f2f2),
    background90 = Color(0xFF0d0d0d),
    background80 = Color(0xFF191919),
    error = Color(0xFFD50000)
)

val yellowLightPalette = baseLightPalette.copy(
    base = Color(0xFFFDD835),
    primary = Color(0xFFF9A825)
)

val yellowDarkPalette = baseDarkPalette.copy(
    base = Color(0xFFFDD835),
    primary = Color(0xFFFFEE58)
)

val greenLightPalette = baseLightPalette.copy(
    base = Color(0xFF43A047),
    primary = Color(0xFF1B5E20)
)

val greenDarkPalette = baseDarkPalette.copy(
    base = Color(0xFF43A047),
    primary = Color(0xFF81C784)
)

val blueLightPalette = baseLightPalette.copy(
    base = Color(0xFF1E88E5),
    primary = Color(0xFF0D47A1)
)

val blueDarkPalette = baseDarkPalette.copy(
    base = Color(0xFF1E88E5),
    primary = Color(0xFF64B5F6)
)

val purpleLightPalette = baseLightPalette.copy(
    base = Color(0xFF9C27B0),
    primary = Color(0xFF4A148C)
)

val purpleDarkPalette = baseDarkPalette.copy(
    base = Color(0xFF9C27B0),
    primary = Color(0xFFBA68C8)
)

val redLightPalette = baseLightPalette.copy(
    base = Color(0xFFdc143c),
    primary = Color(0xff9a0e2a)
)

val redDarkPalette = baseDarkPalette.copy(
    base = Color(0xFFdc143c),
    primary = Color(0xFFe75b77)
)

val orangeLightPalette = baseLightPalette.copy(
    base = Color(0xFFFB8C00),
    primary = Color(0xFFEF6C00)
)

val orangeDarkPalette = baseDarkPalette.copy(
    base = Color(0xFFFB8C00),
    primary = Color(0xFFFFA726)
)