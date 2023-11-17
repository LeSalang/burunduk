package com.lesa.burunduk.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lesa.burunduk.R
import com.lesa.burunduk.data.settings.ProtoDataStoreManager
import com.lesa.burunduk.ui.components.MyText
import com.lesa.burunduk.ui.theme.LeSaModeStyle
import com.lesa.burunduk.ui.theme.LeSaTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DarkModePicker(
    protoDataStoreManager: ProtoDataStoreManager,
    coroutine: CoroutineScope
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        MyText(
            text = stringResource(id = R.string.pick_mode),
            modifier = Modifier
                .weight(1f)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .weight(3f)
                .padding(vertical = LeSaTheme.shape.padding)
        ) {
            LeSaModeStyle.values().forEach {
                FloatingActionButton(
                    onClick = {
                        coroutine.launch {
                            protoDataStoreManager.saveMode(it)
                        }
                    },
                    containerColor = LeSaTheme.colors.primary,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp)
                ) {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = stringResource(id = it.description),
                        tint = LeSaTheme.colors.background80
                    )
                }
            }
        }
    }
}
