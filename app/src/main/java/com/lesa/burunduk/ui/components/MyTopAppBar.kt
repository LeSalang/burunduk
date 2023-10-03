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
import com.lesa.burunduk.ui.theme.BlackBlue
import com.lesa.burunduk.ui.theme.WhiteBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    currentScreen: Destinations
) {
    TopAppBar(
        title = {
            MyHeadline(text = currentScreen.route)
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(id = R.string.settings)
                )
            }
        },
        colors = topAppBarColors(
            containerColor = WhiteBlue,
            actionIconContentColor = BlackBlue
        )
    )
}