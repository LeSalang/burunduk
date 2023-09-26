package com.lesa.burunduk.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lesa.burunduk.ui.components.MyTextBold
import com.lesa.burunduk.ui.theme.WhiteRed

@Composable
fun SumTableView(
    sumForPeriod: HomeUiState
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = WhiteRed),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            Modifier.padding(10.dp)
        ) {
            MyTextBold(
                text = "Expenses per day = ${sumForPeriod.getSumForPeriod("dd MM yyyy")} \u20BD"
            )
            MyTextBold(
                text = "Expenses per week = ${sumForPeriod.getSumForPeriod("ww yyyy")} \u20BD"
            )
            MyTextBold(
                text = "Expenses per month = ${sumForPeriod.getSumForPeriod("MM yyyy")} \u20BD"
            )
        }
    }
}