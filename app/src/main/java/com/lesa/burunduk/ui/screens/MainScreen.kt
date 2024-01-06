package com.lesa.burunduk.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lesa.burunduk.data.settings.ProtoDataStoreManager
import com.lesa.burunduk.ui.components.MyBottomAppBar
import com.lesa.burunduk.ui.components.MyTopAppBar
import com.lesa.burunduk.ui.navigation.Home
import com.lesa.burunduk.ui.navigation.MyNavHost
import com.lesa.burunduk.ui.navigation.navigateSingleTopTo
import com.lesa.burunduk.ui.navigation.screens
import com.lesa.burunduk.ui.theme.LeSaTheme

typealias FABConfigurator = () -> Unit
@Composable
fun MainScreen(
    protoDataStoreManager: ProtoDataStoreManager
) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    var currentScreen =
       screens.find { it.route == currentDestination?.route?.substringBefore('?') } ?: Home
    val (fabOnClick, setFabOnClick) = remember { mutableStateOf<FABConfigurator?>(null) }
    Scaffold(
        topBar = {
            MyTopAppBar(
                currentScreen = currentScreen,
                onButtonSelected = { newScreen ->
                    navController.navigateSingleTopTo(newScreen.route)
                    currentScreen = newScreen
                }
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
                fabConfigurator = fabOnClick
            )
        },
        content = { innerPadding ->
            MyNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                setFABConfigurator = setFabOnClick,
                protoDataStoreManager = protoDataStoreManager,
                //context = context
            )
        },
        containerColor = LeSaTheme.colors.background80
    )
}
