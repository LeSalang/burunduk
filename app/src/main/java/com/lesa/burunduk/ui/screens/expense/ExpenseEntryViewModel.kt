package com.lesa.burunduk.ui.screens.expense

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import co.yml.charts.common.extensions.isNotNull
import com.lesa.burunduk.data.expenses.ExpensesRepository
import com.lesa.burunduk.data.expenses.models.Category
import com.lesa.burunduk.data.expenses.models.Expense
import java.time.LocalDateTime
import java.util.UUID
import kotlin.math.roundToInt

class ExpenseEntryViewModel(private val expensesRepository: ExpensesRepository): ViewModel() {

    var expenseUiState by mutableStateOf(ExpenseUiState())
        private set

    fun validateInput() {
        expenseUiState.validationResult = expenseUiState.expenseDetails.validate()
    }

    fun updateUiState(expenseDetails: ExpenseDetails) {
        expenseUiState =
            ExpenseUiState(expenseDetails = expenseDetails)
    }

    suspend fun saveExpense() {
        expenseUiState.expenseDetails.toExpense()?.let { expensesRepository.insertExpense(it) }
    }
}

data class ExpenseUiState(
    val expenseDetails: ExpenseDetails = ExpenseDetails(),
    var validationResult: ExpenseValidationResult = ExpenseValidationResult()
)

data class ExpenseDetails(
    val category: Category? = null,
    val local: String = "",
    val exchangeRate: String = "",
)

data class ExpenseValidationResult(
    val isCategoryValid: Boolean? = null,
    val isLocalValid: Boolean? = null,
    val isExchangeRateValid: Boolean? = null
)

val ExpenseValidationResult.isValid: Boolean
    get() {
       return isCategoryValid == true && isLocalValid == true && isExchangeRateValid == true
    }

fun ExpenseDetails.validate(): ExpenseValidationResult {
    return ExpenseValidationResult(
        isCategoryValid = category.isNotNull(),
        isLocalValid = (local.toDoubleOrNull() ?: 0.0) > 0.0,
        isExchangeRateValid = (exchangeRate.toDoubleOrNull() ?: 0.0) > 0.0
    )
}

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

