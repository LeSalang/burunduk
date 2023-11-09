package com.lesa.burunduk.ui.screens.home.expenseTableView

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.lesa.burunduk.ui.components.MyText

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReducedExpenseCard(
    expanded: Boolean,
    onExpandStateChanged: (Boolean) -> Unit,
    date: String,
    category: String,
    local: String,
    rub: String
) {
    Row(
        modifier = Modifier
        .fillMaxSize()
        .combinedClickable(
            onLongClickLabel = "LongClick",
            onLongClick = {
                onExpandStateChanged(!expanded)
            },
            onClick = {}
        )
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MyText(
            text = date,
            Modifier.weight(1.6f)
        )
        Spacer(modifier = Modifier.weight(0.1f))
        MyText(
            text = category,
            modifier = Modifier.weight(2.5f)
        )
        Spacer(modifier = Modifier.weight(0.1f))
        MyText(
            text = local,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(0.1f))
        MyText(
            text = rub,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Center
        )
    }
}