package com.lesa.burunduk.data.settings

import android.content.Context
import androidx.datastore.dataStore
import com.lesa.burunduk.ui.theme.LeSaColorStyle
import com.lesa.burunduk.ui.theme.LeSaModeStyle

private val Context.protoDataStore by dataStore("settings.json", SettingsSerializer)
class ProtoDataStoreManager(val context: Context) {

    suspend fun saveColorStyle(colorStyle: LeSaColorStyle) {
        context.protoDataStore.updateData {
            it.copy(colorStyle = colorStyle)
        }
    }

    suspend fun saveMode(mode: LeSaModeStyle) {
        context.protoDataStore.updateData {
            it.copy(mode = mode)
        }
    }

    fun getSettings() = context.protoDataStore.data
}