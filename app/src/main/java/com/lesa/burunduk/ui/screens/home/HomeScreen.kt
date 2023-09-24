package com.lesa.burunduk.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lesa.burunduk.ui.AppViewModelProvider
import com.lesa.burunduk.ui.theme.WhiteBlue
import com.lesa.burunduk.ui.theme.WhiteRed

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    Column(
        androidx.compose.ui.Modifier
            .padding(
                start = 10.dp,
                end = 10.dp
            )
            .background(WhiteBlue)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = WhiteRed),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                Modifier.padding(10.dp)
            ) {
                Text(text = "Expenses per day = ")
                Text(text = "Expenses per week = ")
                Text(text = "Expenses per month = ")
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        ExpensesTableView(
            homeUiState = homeUiState,
            viewModel = viewModel
        )
    }
}