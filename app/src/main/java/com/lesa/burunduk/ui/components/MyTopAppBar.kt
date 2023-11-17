package com.lesa.burunduk.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lesa.burunduk.R
import com.lesa.burunduk.ui.navigation.Destinations
import com.lesa.burunduk.ui.navigation.Settings
import com.lesa.burunduk.ui.theme.LeSaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    currentScreen: Destinations,
    onButtonSelected: (Destinations) -> Unit
) {
    TopAppBar(
        title = {
            MyHeadline(text = currentScreen.route)
        },
        actions = {
            IconButton(
                onClick = { onButtonSelected(Settings) }
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(id = R.string.settings),
                    tint = if (Settings == currentScreen)
                        LeSaTheme.colors.primary
                    else LeSaTheme.colors.text
                )
            }
        },
        colors = topAppBarColors(
            containerColor = LeSaTheme.colors.background80,
            actionIconContentColor = LeSaTheme.colors.text
        )
    )
}