package com.lesa.burunduk.data.expenses

import com.lesa.burunduk.data.expenses.models.Expense
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ExpensesRepository {

    fun getAllExpensesStream(): Flow<List<Expense>>
/*
    fun getExpenseStream(id: UUID): Flow<Expense?>
*/
    suspend fun insertExpense(expense: Expense)

    suspend fun deleteExpense(id: UUID)

    suspend fun updateExpense(expense: Expense)
}