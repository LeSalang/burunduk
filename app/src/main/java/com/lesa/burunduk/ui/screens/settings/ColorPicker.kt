package com.lesa.burunduk.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lesa.burunduk.R
import com.lesa.burunduk.data.settings.ProtoDataStoreManager
import com.lesa.burunduk.ui.components.MyText
import com.lesa.burunduk.ui.theme.LeSaColorStyle
import com.lesa.burunduk.ui.theme.LeSaTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ColorPicker(
    protoDataStoreManager: ProtoDataStoreManager,
    coroutine: CoroutineScope
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        MyText(
            text = stringResource(id = R.string.pick_color),
            modifier = Modifier
                .weight(1f)
        )
        Column(
            modifier = Modifier
                .weight(3f)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = LeSaTheme.shape.padding)
            ){
                LeSaColorStyle.values().dropLast(1).forEachIndexed { index, color ->
                    if (index in 0..2) {
                        FloatingActionButton(
                            onClick = {
                                coroutine.launch {
                                    protoDataStoreManager.saveColorStyle(color)
                                }
                            },
                            containerColor = if (LeSaTheme.mode.isDarkMode) color.darkColor else color.lightColor,
                            elevation = FloatingActionButtonDefaults.elevation(0.dp)
                        ) {
                        }
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = LeSaTheme.shape.padding)
            ){
                LeSaColorStyle.values().dropLast(1).forEachIndexed { index, color ->
                    if (index in 3..5) {
                        FloatingActionButton(
                            onClick = {
                                coroutine.launch {
                                    protoDataStoreManager.saveColorStyle(color)
                                }
                            },
                            containerColor = if (LeSaTheme.mode.isDarkMode) color.darkColor else color.lightColor,
                            elevation = FloatingActionButtonDefaults.elevation(0.dp)
                        ) {
                        }
                    }
                }
            }
        }
    }
}
