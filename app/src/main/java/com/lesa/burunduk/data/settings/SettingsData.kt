package com.lesa.burunduk.data.settings

import com.lesa.burunduk.ui.theme.LeSaColorStyle
import com.lesa.burunduk.ui.theme.LeSaModeStyle
import kotlinx.serialization.Serializable

@Serializable
data class SettingsData(
    val colorStyle: LeSaColorStyle = LeSaColorStyle.Base,
    val mode: LeSaModeStyle = LeSaModeStyle.SystemMode
)
