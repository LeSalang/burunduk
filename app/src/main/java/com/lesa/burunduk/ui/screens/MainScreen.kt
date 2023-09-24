package com.lesa.burunduk.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lesa.burunduk.ui.components.MyBottomAppBar
import com.lesa.burunduk.ui.components.MyTopAppBar
import com.lesa.burunduk.ui.navigation.Home
import com.lesa.burunduk.ui.navigation.MyNavHost
import com.lesa.burunduk.ui.navigation.navigateSingleTopTo
import com.lesa.burunduk.ui.navigation.screens
import com.lesa.burunduk.ui.theme.WhiteBlue

@Preview
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    var currentScreen =
       screens.find { it.route == currentDestination?.route } ?: Home
    val dialogState = remember {
        mutableStateOf(false)
    }


    Scaffold(
        topBar = {
            MyTopAppBar(
                currentScreen = currentScreen
            )
        },
        bottomBar = {
            MyBottomAppBar(
                allScreens = screens,
                onButtonSelected = { newScreen ->
                    navController.navigateSingleTopTo(newScreen.route)
                    currentScreen = newScreen
                },
                currentScreen = currentScreen,
                //dialogState = dialogState
            )
        },
        content = { innerPadding ->
            MyNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        },
        containerColor = WhiteBlue
    )
}
