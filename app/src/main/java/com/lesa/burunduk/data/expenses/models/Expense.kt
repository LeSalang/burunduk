package com.lesa.burunduk.data.expenses.models

import java.time.LocalDateTime
import java.util.UUID

data class Expense(
    val id: UUID,
    val date: LocalDateTime,
    val category: Category,
    val local: Int,
    val exchangeRate: Double,
    val kopecks: Int
)