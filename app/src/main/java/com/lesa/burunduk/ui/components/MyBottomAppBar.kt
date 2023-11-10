package com.lesa.burunduk.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.lesa.burunduk.ui.navigation.AddExpense
import com.lesa.burunduk.ui.navigation.Destinations
import com.lesa.burunduk.ui.screens.FABConfigurator
import com.lesa.burunduk.ui.theme.BlackBlue
import com.lesa.burunduk.ui.theme.Red
import com.lesa.burunduk.ui.theme.WhiteBlue



@Composable
fun MyBottomAppBar(
    allScreens: List<Destinations>,
    onButtonSelected: (Destinations) -> Unit,
    currentScreen: Destinations,
    fabConfigurator: FABConfigurator?
) {

    BottomAppBar(
        actions = {
            allScreens.dropLast(1).forEach { screen ->
                IconButton(onClick = {
                    onButtonSelected(screen)
                }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.route,
                            tint = if (screen == currentScreen) Red else BlackBlue
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = fabConfigurator ?: { onButtonSelected(AddExpense) },
                containerColor = BlackBlue,
                contentColor = Red,
                modifier = Modifier
            ) {
                Icon(
                    imageVector = if (currentScreen == AddExpense) Icons.Default.Done else Icons.Default.Add,
                    contentDescription = ""
                )
            }
        },
        containerColor = WhiteBlue
    )
}