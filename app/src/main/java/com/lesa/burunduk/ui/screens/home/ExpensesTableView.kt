package com.lesa.burunduk.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lesa.burunduk.R
import com.lesa.burunduk.ui.components.MyTextBold
import com.lesa.burunduk.ui.theme.WhiteBlue
import com.lesa.burunduk.ui.theme.WhiteRed
import kotlinx.coroutines.launch

@Composable
fun ExpensesTableView(
    homeUiState: List<HomeScreenExpense>,
    viewModel: HomeViewModel,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()

    Card(
        colors = CardDefaults.cardColors(containerColor = WhiteRed),
        elevation = CardDefaults.cardElevation(defaultElevation = (-10).dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier.padding(10.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MyTextBold(text = stringResource(id = R.string.date), modifier = Modifier.weight(1.5f))
                Spacer(modifier = Modifier.weight(0.1f))
                MyTextBold(text = stringResource(id = R.string.category), modifier = Modifier.weight(3f))
                Spacer(modifier = Modifier.weight(0.1f))
                MyTextBold(
                    text = stringResource(id = R.string.local),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(0.1f))
                MyTextBold(
                    text = stringResource(id = R.string.rub),
                    modifier = Modifier.weight(2f),
                    textAlign = TextAlign.Center
                )
            }
            HorizontalDivider(color = WhiteBlue)
            LazyColumn() {
                items(homeUiState) { expense ->
                    Spacer(modifier = Modifier.height(8.dp))
                    var dropdownMenuExpanded by remember(
                        key1 = expense.id
                    ) {
                        mutableStateOf(false)
                    }
                    ExpenseCard(
                        isExpanded = dropdownMenuExpanded,
                        onExpandStateChanged = { newExpandedValue ->
                            dropdownMenuExpanded = newExpandedValue
                        },
                        onDeleteClick = {
                            coroutineScope.launch { viewModel.deleteExpense(expense.id) }
                        },
                        date = expense.date,
                        category = stringResource(id = expense.category),
                        local = expense.local,
                        rub = expense.rub,
                        navController = navController
                    )
                    HorizontalDivider(color = WhiteBlue)
                }
            }
        }
    }
}

@Composable
fun ExpenseCard(
    isExpanded: Boolean,
    onExpandStateChanged: (Boolean) -> Unit,
    onDeleteClick: () -> Unit,
    date: String,
    category: String,
    local: String,
    rub: String,
    navController: NavController
) {
    if (isExpanded) {
        ExpandedExpenseCard(
            expanded = isExpanded,
            onExpandStateChanged = onExpandStateChanged,
            onDeleteClick = onDeleteClick,
            date = date,
            category = category,
            local = local,
            rub = rub,
            navController = navController
        )
    } else {
        ReducedExpenseCard(
            expanded = isExpanded,
            onExpandStateChanged = onExpandStateChanged,
            date = date,
            category = category,
            local = local,
            rub = rub
        )
    }
}
