package com.lesa.burunduk.ui.screens.stats

import java.time.LocalDate

data class StatsScreenExpense(
    val date: LocalDate,
    val category: String,
    var rub: Int
)

data class StatsScreenExpenseForLineChart(
    val date: LocalDate,
    var sotka: Int
)
