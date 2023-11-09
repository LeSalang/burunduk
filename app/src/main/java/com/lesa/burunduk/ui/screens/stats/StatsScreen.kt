package com.lesa.burunduk.ui.screens.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lesa.burunduk.ui.AppViewModelProvider
import com.lesa.burunduk.ui.theme.WhiteBlue
import com.lesa.burunduk.ui.theme.WhiteRed

@Composable
fun StatsScreen(
    viewModel: StatsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val statsUiState by viewModel.statsUiState.collectAsState()
    val listOfYears = statsUiState.getListOfYears().map {
        StatsSelectorItem(
            value = it,
            name = it.toString()
        )
    }
    val statsSelectorItemYear = remember {
        mutableStateOf(listOfYears.last())
    }
    val isMonthMenuExpanded = remember { mutableStateOf(false) }
    val isYearMenuExpanded = remember { mutableStateOf(false) }
    val listOfMonths = java.time.Month.values().map { it.toStatsSelectorItem() }

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
            LazyColumn(
                Modifier.padding(10.dp)
            ) {
                item {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                        ,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        StatsSelector(
                            modifier = Modifier,
                            isExpanded = isMonthMenuExpanded,
                            selectedMenuItem = statsUiState.month.value.toStatsSelectorItem(),
                            listOfMenuItems = listOfMonths,
                            onSelect = {
                                viewModel.selectMonth(it.value)
                                //statsUiState.month.value = it.value
                            }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        StatsSelector(
                            modifier = Modifier,
                            isExpanded = isYearMenuExpanded,
                            selectedMenuItem = statsUiState.year.value.toStatsSelectorItem(),
                            listOfMenuItems = listOfYears,
                            onSelect = {
                                viewModel.selectYear(it.value)
                                //statsUiState.year.value = it.value
                            }
                        )
                    }
                    StatsLineChart(
                        state = statsUiState.lineChartState
                    )
                }
            }
        }
    }
}

private fun java.time.Month.toStatsSelectorItem(): StatsSelectorItem<java.time.Month> {
    return StatsSelectorItem(
        value = this,
        name = name
    )
}

private fun java.time.Year.toStatsSelectorItem(): StatsSelectorItem<java.time.Year> {
    return StatsSelectorItem(
        value = this,
        name = value.toString()
    )
}








