package com.lesa.burunduk.ui.screens.home

import java.util.UUID

data class HomeScreenExpense(
    val id: UUID,
    val date: String,
    val category: Int,
    val local: String,
    val rub: String
)
