package com.lesa.burunduk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.lesa.burunduk.data.settings.ProtoDataStoreManager
import com.lesa.burunduk.data.settings.SettingsData
import com.lesa.burunduk.ui.screens.MainScreen
import com.lesa.burunduk.ui.theme.LeSaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val protoDataStoreManager = ProtoDataStoreManager(this)
        setContent {
            val settingsState = protoDataStoreManager.getSettings().collectAsState(initial = SettingsData())
            //val isDarkMode = isSystemInDarkTheme()
            LeSaTheme(
                colorStyle = settingsState.value.colorStyle,
                modeStyle = settingsState.value.mode
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = LeSaTheme.colors.background80
                ) {
                    MainScreen(
                        //context = this,
                        protoDataStoreManager = protoDataStoreManager
                    )
                }
            }
        }

    }
}
