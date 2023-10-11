package com.lesa.burunduk.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lesa.burunduk.ui.screens.PlansScreen
import com.lesa.burunduk.ui.screens.expense.ExpenseEntryScreen
import com.lesa.burunduk.ui.screens.home.HomeScreen
import com.lesa.burunduk.ui.screens.home.HomeViewModel
import com.lesa.burunduk.ui.screens.stats.StatsScreen

@Composable
fun MyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(route = Home.route) {
            HomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = Plans.route) {
            PlansScreen()
        }
        composable(route = Stats.route) {
            StatsScreen(
                viewModel = viewModel
            )
        }
        composable(route = AddExpense.route) {
            ExpenseEntryScreen(
                navigateBack = {
                    navController.navigateUp()
                }
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