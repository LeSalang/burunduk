package com.lesa.burunduk.ui.screens.stats

import android.util.Log
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
import com.lesa.burunduk.ui.screens.home.HomeViewModel
import com.lesa.burunduk.ui.screens.home.getListOfYears
import com.lesa.burunduk.ui.theme.WhiteBlue
import com.lesa.burunduk.ui.theme.WhiteRed
import java.time.YearMonth

@Composable
fun StatsScreen(
    viewModel: HomeViewModel
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    val currentDate = YearMonth.now()
    val currentMonth = currentDate.month.name
    val currentYear = currentDate.year.toString()

    Log.d("MyLog", currentMonth)

    val isMonthMenuExpanded = remember { mutableStateOf(false) }
    val listOfMonths = Month.values().map { it.name }
    val selectedMonth = remember { mutableStateOf(currentMonth) }

    val isYearMenuExpanded = remember { mutableStateOf(false) }
    val listOfYears = homeUiState.getListOfYears()
    val selectedYear = remember { mutableStateOf(currentYear) }

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
                            selectedMenuItem = selectedMonth,
                            listOfMenuItems = listOfMonths
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        StatsSelector(
                            modifier = Modifier,
                            isExpanded = isYearMenuExpanded,
                            selectedMenuItem = selectedYear,
                            listOfMenuItems = listOfYears
                        )
                    }
                    MyLineChart(
                        homeUiState = homeUiState,
                        selectedMonth = selectedMonth
                    )
                }
            }
        }
    }
}







