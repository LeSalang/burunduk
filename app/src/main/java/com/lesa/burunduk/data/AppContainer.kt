package com.lesa.burunduk.data

import android.content.Context
import com.lesa.burunduk.data.expenses.ExpenseRoomDataBase
import com.lesa.burunduk.data.expenses.ExpensesRepository
import com.lesa.burunduk.data.expenses.OfflineExpensesRepository

interface AppContainer {
    val expensesRepository: ExpensesRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val expensesRepository: ExpensesRepository by lazy {
        OfflineExpensesRepository(ExpenseRoomDataBase.getDataBase(context).expenseDao())
    }
}