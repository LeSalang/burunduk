package com.lesa.burunduk.ui.screens.home.expenseTableView

import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lesa.burunduk.R
import com.lesa.burunduk.ui.components.MyTextBold
import com.lesa.burunduk.ui.screens.home.HomeUiState
import com.lesa.burunduk.ui.screens.home.HomeViewModel
import com.lesa.burunduk.ui.screens.home.toHomeScreen
import com.lesa.burunduk.ui.theme.BlackBlue
import com.lesa.burunduk.ui.theme.Red
import com.lesa.burunduk.ui.theme.WhiteBlue
import com.lesa.burunduk.ui.theme.WhiteRed
import kotlinx.coroutines.launch

@Composable
fun ExpensesTableView(
    homeUiState: HomeUiState,
    viewModel: HomeViewModel,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val sortParameter = rememberSaveable { mutableStateOf(ExpenseSortSelector.DATE) }
    val sortDirection = rememberSaveable { mutableStateOf(true) }
    val isCategoryMenuDropDown = remember { mutableStateOf(false) }
    val selectedCategory = rememberSaveable { mutableIntStateOf(R.string.category_all) }
    Card(
        colors = CardDefaults.cardColors(containerColor = WhiteRed),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
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
                MyTextBold(
                    text = stringResource(ExpenseSortSelector.DATE.title),
                    color = if (sortParameter.value == ExpenseSortSelector.DATE) Red else BlackBlue,
                    modifier = Modifier
                        .weight(1.5f)
                        .clickable {
                            sortParameter.value = ExpenseSortSelector.DATE
                            sortDirection.value = !sortDirection.value
                        }
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Row(
                    modifier = Modifier
                        .weight(2.5f)
                ) {
                    MyTextBold(
                        text = stringResource(ExpenseSortSelector.CATEGORY.title),
                        color = if (sortParameter.value == ExpenseSortSelector.CATEGORY) Red else BlackBlue,
                        modifier = Modifier
                           // .weight(2.5f)
                            .clickable {
                                sortParameter.value = ExpenseSortSelector.CATEGORY
                                sortDirection.value = !sortDirection.value
                            }
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "",
                        tint = if (sortParameter.value == ExpenseSortSelector.CATEGORY) Red else BlackBlue,
                        modifier = Modifier
                            .clickable {
                                isCategoryMenuDropDown.value = !isCategoryMenuDropDown.value
                            }
                    )
                    CategoryDropDownMenu(
                        isExpanded = isCategoryMenuDropDown,
                        onDismissRequest = {
                            isCategoryMenuDropDown.value = !isCategoryMenuDropDown.value
                        },
                        selectedCategory = selectedCategory
                    )
                }

                Spacer(modifier = Modifier.weight(0.1f))
                MyTextBold(
                    text = stringResource(id = R.string.local),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(0.1f))
                MyTextBold(
                    text = stringResource(ExpenseSortSelector.RUB.title),
                    color = if (sortParameter.value == ExpenseSortSelector.RUB) Red else BlackBlue,
                    modifier = Modifier
                        .weight(2f)
                        .clickable {
                            sortParameter.value = ExpenseSortSelector.RUB
                            sortDirection.value = !sortDirection.value
                        }
                    ,
                    textAlign = TextAlign.Center
                )
            }
            HorizontalDivider(color = WhiteBlue)
            LazyColumn() {
                items(homeUiState.toHomeScreen(
                    sortParameter.value,
                    sortDirection.value,
                    selectedCategory = selectedCategory.value)
                ) { expense ->
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

enum class ExpenseSortSelector(val title: Int) {
    DATE(R.string.date),
    CATEGORY(R.string.category),
    LOCAL(R.string.local),
    RUB(R.string.rub)
}