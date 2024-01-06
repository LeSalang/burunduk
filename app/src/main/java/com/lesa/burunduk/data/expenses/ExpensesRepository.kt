package com.lesa.burunduk.data.expenses

import com.lesa.burunduk.data.expenses.models.Expense
import com.lesa.burunduk.ui.screens.expenseEntry.ExpenseDetails
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ExpensesRepository {

    fun getAllExpensesStream(): Flow<List<Expense>>

    fun getSumPerDay(): Flow<Int>
/*
    fun getExpenseStream(id: UUID): Flow<Expense?>
*/
    suspend fun insertExpense(expense: Expense)

    suspend fun deleteExpense(id: UUID)

    suspend fun findExpenseById(id: UUID): ExpenseDetails
    //suspend fun findExpenseById(id: UUID): Expense

    //suspend fun updateExpense(expense: Expense)
}