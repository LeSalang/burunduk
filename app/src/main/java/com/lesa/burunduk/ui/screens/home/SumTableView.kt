package com.lesa.burunduk.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lesa.burunduk.R
import com.lesa.burunduk.ui.components.MyHeadline
import com.lesa.burunduk.ui.components.MyText
import com.lesa.burunduk.ui.theme.LeSaTheme

@Composable
fun SumTableView(
    sumForPeriod: HomeUiState
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = LeSaTheme.colors.background80),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SumExpenseForPeriod(
                sum = sumForPeriod.getSumForPeriod("dd MM yyyy").toString() + " \u20BD",
                period = stringResource(id = R.string.for_day)
            )
            SumExpenseForPeriod(
                sum = sumForPeriod.getSumForPeriod("ww yyyy").toString() + " \u20BD",
                period = stringResource(id = R.string.for_week)
            )
            SumExpenseForPeriod(
                sum = sumForPeriod.getSumForPeriod("MM yyyy").toString() + " \u20BD",
                period = stringResource(id = R.string.for_month)
            )
        }
        
        /*Column(
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
        }*/
    }
}

@Composable
fun SumExpenseForPeriod(
    sum: String,
    period: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyHeadline(
            text = sum,
            //fontSize = 24.sp
        )
        MyText(text = period)
    }
}