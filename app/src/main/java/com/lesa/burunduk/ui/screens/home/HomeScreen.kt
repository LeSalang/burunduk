package com.lesa.burunduk.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lesa.burunduk.ui.AppViewModelProvider
import com.lesa.burunduk.ui.screens.home.expenseTableView.ExpensesTableView
import com.lesa.burunduk.ui.theme.LeSaTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavController
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    Column(
        Modifier
            .padding(
                start = 10.dp,
                end = 10.dp
            )
            .background(LeSaTheme.colors.background80)
    ) {
        SumTableView(
            sumForPeriod = homeUiState
        )
        Spacer(modifier = Modifier.size(10.dp))
        ExpensesTableView(
            homeUiState = homeUiState,
            viewModel = viewModel,
            navController = navController
        )
    }
}