package com.lesa.burunduk.ui.screens.stats

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.charts.common.model.Point
import co.yml.charts.ui.piechart.models.PieChartData
import com.lesa.burunduk.data.expenses.ExpensesRepository
import com.lesa.burunduk.data.expenses.models.Category
import com.lesa.burunduk.data.expenses.models.Expense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Month
import java.time.Year
import java.time.YearMonth

class StatsViewModel(private val expensesRepository: ExpensesRepository): ViewModel() {
    val statsUiState: MutableStateFlow<StatsUiState>

    init {
        statsUiState = run {
            val date = LocalDate.now()
            val month = date.month
            val year = Year.now()
            MutableStateFlow(
                StatsUiState(
                    expensesList = listOf(),
                    lineChartState = StatsLineChartState(
                        points = getListOfPoints(
                            year = year.value,
                            month = month.value,
                            list = listOf()
                        )
                    ),
                    month = mutableStateOf(month),
                    year = mutableStateOf(year)
                )
            )
        }

        viewModelScope.launch {
            /*
            snapshotFlow {
                statsUiState.value.year
            }.collect { year ->
                statsUiState.value.lineChartState.points = getListOfPoints(
                    year = year.value.value,
                    month = statsUiState.value.month.value.value,
                    list = statsUiState.value.expensesList
                )
            }*/

           /* snapshotFlow {
                statsUiState
            }.
            */
            expensesRepository.getAllExpensesStream().onEach {
                statsUiState.value = StatsUiState(
                    expensesList = it,
                    lineChartState = StatsLineChartState(
                        getListOfPoints(
                            year = statsUiState.value.year.value.value,
                            month = statsUiState.value.month.value.value,
                            list = it
                        )
                    ),
                    month = statsUiState.value.month,
                    year = statsUiState.value.year
                )
            }.launchIn(this)
        }
    }

    fun selectMonth(month: Month) {
        statsUiState.value = StatsUiState(
            expensesList = statsUiState.value.expensesList,
            lineChartState = StatsLineChartState(
                getListOfPoints(
                    year = statsUiState.value.year.value.value,
                    month = month.value,
                    list = statsUiState.value.expensesList
                )
            ),
            month = mutableStateOf(month),
            year = statsUiState.value.year
        )
    }

    fun selectYear(year: Year) {
        statsUiState.value = StatsUiState(
            expensesList = statsUiState.value.expensesList,
            lineChartState = StatsLineChartState(
                getListOfPoints(
                    year = year.value,
                    month = statsUiState.value.month.value.value,
                    list = statsUiState.value.expensesList
                )
            ),
            month = statsUiState.value.month,
            year = mutableStateOf(year)
        )
    }
}

class StatsUiState(
    val expensesList: List<Expense>,
    val lineChartState: StatsLineChartState,
    val month: MutableState<Month>,
    val year: MutableState<Year>
)
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

fun getMapOfDateToSum(year: Int, month: Int, list: List<Expense>): MutableMap<LocalDate, Int> {
    val statsExpenseList =  list.map {
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

fun getListOfPoints(year: Int, month: Int, list: List<Expense>): ArrayList<Point> {
    val array = ArrayList<Point>()
    val listOfDateToSum = getMapOfDateToSum(year, month, list)
    listOfDateToSum.forEach { (date, sum) ->
        array.add(
            Point(
                x = date.dayOfMonth.toFloat(),
                y = sum.toFloat()
            )
        )
    }
    return array
}

fun StatsUiState.getMapOfCategoryToSum(year: Int, month: Int): MutableMap<Category, Int> {
    val list = ArrayList<PieChartData>()
    val statsExpenseList =  expensesList.map {
        it.toStatsScreenExpense()
    }.filter { it.date.year == year && it.date.month.value == month }
    val mapOfCategoryToSum = mutableMapOf<Category, Int>()
    Category.values().forEach {category ->
        val sum = statsExpenseList.filter { it.category == category.name }.sumOf { it.rub }
        mapOfCategoryToSum[category] = sum
    }
    return mapOfCategoryToSum
}

fun StatsUiState.getListOfYears(): List<Year> {
    val years = mutableSetOf<Year>()
    expensesList.forEach { expense ->
        val yearInt = expense.date.year
        val year = Year.of(yearInt)
        years.add(year)
    }
    if (expensesList.isEmpty()) years.add(Year.now())
    return years.toList().sorted()
}