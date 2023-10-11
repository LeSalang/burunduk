package com.lesa.burunduk.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.model.Point
import com.lesa.burunduk.R
import com.lesa.burunduk.data.expenses.ExpensesRepository
import com.lesa.burunduk.data.expenses.models.Expense
import com.lesa.burunduk.data.expenses.models.nameId
import com.lesa.burunduk.ui.screens.home.expenseTableView.TitlesOfTableView
import com.lesa.burunduk.ui.screens.stats.StatsScreenExpense
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
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
    val formatter = DateTimeFormatter.ofPattern("MM.dd", Locale.getDefault())
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
    sortParameter: TitlesOfTableView,
    sortDirection: Boolean,
    selectedCategory: Int
): List<HomeScreenExpense> {
    val homeScreenExpenseList = expensesList.map {
        it.toHomeScreen()
    }
    val selectedExpensesList =
        if (selectedCategory != R.string.category_all) {
            homeScreenExpenseList.filter {
                it.category == selectedCategory
            }
        } else {
            homeScreenExpenseList
    }

    return if (sortDirection) {
        when (sortParameter) {
            TitlesOfTableView.DATE -> {
                selectedExpensesList.sortedByDescending{
                    it.date
                }
            }
            TitlesOfTableView.CATEGORY-> {
                selectedExpensesList.sortedByDescending{
                    it.category
                }
            }
            else -> {
                selectedExpensesList.sortedByDescending{
                    it.rub.toInt()
                }
            }
        }
    } else {
        when (sortParameter) {
            TitlesOfTableView.DATE -> {
                selectedExpensesList.sortedBy{
                    it.date
                }
            }
            TitlesOfTableView.CATEGORY-> {
                selectedExpensesList.sortedBy{
                    it.category
                }
            }
            else -> {
                selectedExpensesList.sortedBy{
                    it.rub.toInt()
                }
            }
        }
    }
}

private fun priceRounder(price: Int) : String {
    val rub: Float = price.toFloat() / 100f
    val kopecks = rub - rub.toInt()
    return if (kopecks == 0f) {
        rub.toInt().toString()
    } else {
        "%.2f".format(rub)
    }
}

private fun Expense.toStatsScreenExpense(): StatsScreenExpense {
    val rub = kopecks / 100
    return StatsScreenExpense(
        date = date.toLocalDate(),
        category = category.name,
        rub = rub
    )
}

fun getDaysInMonth(year: Int, month: Int): List<LocalDate> {
    val yearMonth = YearMonth.of(year, month)
    val daysInMonth = yearMonth.lengthOfMonth()
    val daysList = mutableListOf<LocalDate>()
    for (dayOfMonth in 1..daysInMonth) {
        val date = LocalDate.of(year, month, dayOfMonth)
        daysList.add(date)
    }
    return daysList
}
fun HomeUiState.getMapOfDateToSum(year: Int, month: Int): MutableMap<LocalDate, Int> {
    val statsExpenseList =  expensesList.map {
        it.toStatsScreenExpense()
    }
    val mapOfDateToSum: MutableMap<LocalDate, Int> = mutableMapOf()
    val daysInMonth = getDaysInMonth(year, month)
    daysInMonth.forEach {day ->
        val sum = statsExpenseList.filter { it.date == day }.sumOf { it.rub }
        mapOfDateToSum[day] = sum
    }
    return mapOfDateToSum
}

fun HomeUiState.getListOfPoints(year: Int, month: Int): ArrayList<Point> {
    val list = ArrayList<Point>()
    val listOfDateToSum = getMapOfDateToSum(year, month)
    listOfDateToSum.forEach() { (date, sum) ->
        list.add(
            Point(
                x = date.dayOfMonth.toFloat(),
                y = sum.toFloat()
            )
        )
    }
    return list
}

fun HomeUiState.getListOfYears(): List<String> {
    val years = mutableSetOf<String>()
    expensesList.forEach { expense ->
        val year = expense.date.year.toString()
        years.add(year)
    }
    return years.toList()
}