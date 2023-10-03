package com.lesa.burunduk.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lesa.burunduk.R
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
                text = stringResource(id = R.string.expenses_for_day, sumForPeriod.getSumForPeriod("dd MM yyyy"))
            )
            MyTextBold(
                text = stringResource(id = R.string.expenses_for_week, sumForPeriod.getSumForPeriod("ww yyyy"))
            )
            MyTextBold(
                text = stringResource(id = R.string.expenses_for_month, sumForPeriod.getSumForPeriod("MM yyyy"))
            )
        }
    }
}