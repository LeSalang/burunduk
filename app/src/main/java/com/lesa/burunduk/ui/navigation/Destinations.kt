package com.lesa.burunduk.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

interface Destinations {
    val icon: ImageVector
    val route: String
}

object Home: Destinations {
    override val icon = Icons.Rounded.Home
    override val route = "home"
}

object Plans: Destinations {
    override val icon = Icons.Rounded.ShoppingCart
    override val route = "plans"
}

object Stats: Destinations {
    override val icon = Icons.Rounded.Info
    override val route = "stats"
}

object AddExpense: Destinations {
    override val icon = Icons.Rounded.Add
    override val route = "add expense"
}

object Settings: Destinations {
    override val icon = Icons.Rounded.Settings
    override val route = "settings"
}

val screens = listOf(Stats, Plans, Home, AddExpense, Settings)