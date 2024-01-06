package com.lesa.burunduk.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lesa.burunduk.data.settings.ProtoDataStoreManager
import com.lesa.burunduk.ui.AppViewModelProvider
import com.lesa.burunduk.ui.screens.FABConfigurator
import com.lesa.burunduk.ui.screens.expenseEntry.ExpenseEntryScreen
import com.lesa.burunduk.ui.screens.expenseEntry.ExpenseEntryViewModel
import com.lesa.burunduk.ui.screens.home.HomeScreen
import com.lesa.burunduk.ui.screens.settings.SettingsScreen
import com.lesa.burunduk.ui.screens.shoppingList.ShoppingListScreen
import com.lesa.burunduk.ui.screens.stats.StatsScreen
import java.util.UUID

@Composable
fun MyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    setFABConfigurator: ((FABConfigurator?) -> Unit),
    protoDataStoreManager: ProtoDataStoreManager,
) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(route = Home.route) {
            HomeScreen(
                navController = navController
            )
        }
        composable(route = Plans.route) {
            ShoppingListScreen()
        }
        composable(route = Stats.route) {
            StatsScreen()
        }
        composable(
            route = AddExpense.route + "?expenseID={expenseID}",
            arguments = listOf(navArgument("expenseID") {
                type = NavType.StringType
                nullable = true
            })
        ) {
            val expenseID = it.arguments?.getString("expenseID")?.let { uuidString ->
                UUID.fromString(uuidString)
            }
            val viewModel: ExpenseEntryViewModel = viewModel(
                factory = AppViewModelProvider
                    .expenseEntryViewModelFactory(expenseID)
            )
            ExpenseEntryScreen(
                viewModel = viewModel,
                navigateBack = {
                    navController.navigateUp()
                },
                setFABConfigurator = setFABConfigurator,
                id = expenseID!!
            )
        }
        composable(route = Settings.route) {
            SettingsScreen(
                protoDataStoreManager = protoDataStoreManager,
                //context = context
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        restoreState = true
    }