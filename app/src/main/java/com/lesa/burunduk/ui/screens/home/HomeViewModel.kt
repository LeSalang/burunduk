package com.lesa.burunduk.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lesa.burunduk.R
import com.lesa.burunduk.data.expenses.ExpensesRepository
import com.lesa.burunduk.data.expenses.models.Expense
import com.lesa.burunduk.data.expenses.models.nameId
import com.lesa.burunduk.ui.screens.home.expenseTableView.ExpenseSortSelector
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale
import java.util.UUID

class HomeViewModel(private val expensesRepository: ExpensesRepository): ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        expensesRepository.getAllExpensesStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 500000L
    }

    suspend fun deleteExpense(id: UUID) {
        viewModelScope.launch { expensesRepository.deleteExpense(id) }
    }
}

data class HomeUiState(val expensesList: List<Expense> = listOf())

private fun Expense.toHomeScreen(): HomeScreenExpense {
    val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
    val date = date.format(formatter)
    val local = priceRounder(local)
    val rub = ((kopecks) / 100).toString()
    return HomeScreenExpense(
        id = id,
        date = date,
        category = category.nameId,
        local = local,
        rub = rub
    )
}

fun HomeUiState.getSumForPeriod(pattern: String): Int {
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val currentDay = LocalDateTime.now().format(formatter)
    var sum: Int = 0
    expensesList.forEach {expense ->
        val date = expense.date.format(formatter)
        val kopecks = expense.kopecks / 100
        if (date == currentDay) sum += kopecks
    }
    return sum
}

fun HomeUiState.toHomeScreen(
    sortParameter: ExpenseSortSelector,
    sortDirection: Boolean,
    selectedCategory: Int,
): List<HomeScreenExpense> {
    val result = expensesList
        .filter {
            if (selectedCategory != R.string.category_all) {
                it.category.nameId == selectedCategory
            } else {
                true
            }
        }
        .sortedExpenses(sortParameter, sortDirection)
        .map {
            it.toHomeScreen()
        }
    return result
}

private fun List<Expense>.sortedExpenses(
    selector: ExpenseSortSelector,
    byDescending: Boolean
): List<Expense> {
    return if (byDescending) {
        when (selector) {
            ExpenseSortSelector.DATE -> {
                sortedByDescending { it.date }
            }
            ExpenseSortSelector.CATEGORY-> {
                sortedByDescending { it.category }
            }
            ExpenseSortSelector.RUB -> {
                sortedByDescending { it.kopecks }
            }
            else -> this
        }
    } else {
        when (selector) {
            ExpenseSortSelector.DATE -> {
                sortedBy { it.date }
            }
            ExpenseSortSelector.CATEGORY-> {
                sortedBy { it.category }
            }
            ExpenseSortSelector.RUB -> {
                sortedBy { it.kopecks }
            }
            else -> this
        }
    }
}



private fun priceRounder(price: Int): String {
    val rub: Float = price.toFloat() / 100f
    val kopecks = rub - rub.toInt()
    return if (kopecks == 0f) {
        rub.toInt().toString()
    } else {
        "%.2f".format(rub)
    }
}
