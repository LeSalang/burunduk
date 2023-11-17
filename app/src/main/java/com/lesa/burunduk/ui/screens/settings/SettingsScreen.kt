package com.lesa.burunduk.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lesa.burunduk.data.settings.ProtoDataStoreManager
import com.lesa.burunduk.ui.theme.LeSaTheme

@Composable
fun SettingsScreen(
    protoDataStoreManager: ProtoDataStoreManager,
    //context: Context
) {
    val coroutine = rememberCoroutineScope()

    Column (
        Modifier
            .padding(
                start = 10.dp,
                end = 10.dp
            )

    ){
        Card(
            colors = CardDefaults.cardColors(containerColor = LeSaTheme.colors.background80),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                Modifier
                    .padding(
                        start = 10.dp,
                        end = 10.dp
                    )
                    .fillMaxSize()
            ) {
                ColorPicker(
                    protoDataStoreManager = protoDataStoreManager,
                    coroutine = coroutine
                )
                DarkModePicker(
                    protoDataStoreManager = protoDataStoreManager,
                    coroutine = coroutine
                )
            }
        }
    }
}