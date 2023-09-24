package com.lesa.burunduk.ui.screens.expense

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lesa.burunduk.data.expenses.ExpensesRepository
import com.lesa.burunduk.data.expenses.models.Category
import com.lesa.burunduk.data.expenses.models.Expense
import java.time.LocalDateTime
import java.util.UUID
import kotlin.math.roundToInt

class ExpenseEntryViewModel(private val expensesRepository: ExpensesRepository): ViewModel() {

    var expenseUiState by mutableStateOf(ExpenseUiState())
        private set

    private fun validateInput(expenseDetails: ExpenseDetails = expenseUiState.expenseDetails): Boolean {
        return expenseDetails.toExpense() != null
    }

    fun updateUiState(expenseDetails: ExpenseDetails) {
        expenseUiState =
            ExpenseUiState(expenseDetails = expenseDetails, isEntryValid = validateInput(expenseDetails))
    }

    suspend fun saveExpense() {
        expenseUiState.expenseDetails.toExpense()?.let { expensesRepository.insertExpense(it) }
    }
}

data class ExpenseUiState(
    val expenseDetails: ExpenseDetails = ExpenseDetails(),
    val isEntryValid: Boolean = false
)

data class ExpenseDetails(
    val category: Category? = null,
    val local: String = "",
    val exchangeRate: String = "",
)

fun ExpenseDetails.toExpense(): Expense? {
    if (category == null) return null
    val local = ((local.toDoubleOrNull() ?: 0.0) * 100.0).roundToInt()
    if (local <= 0) return null
    val exchangeRate = (exchangeRate.toDoubleOrNull() ?: 0.0)
    if (exchangeRate <= 0) return null
    return Expense(
        id = UUID.randomUUID(),
        date = LocalDateTime.now(),
        category = category,
        local = local,
        exchangeRate = exchangeRate,
        kopecks = (local.toDouble() * exchangeRate).roundToInt()
    )
}

