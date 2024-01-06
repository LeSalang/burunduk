package com.lesa.burunduk.data.expenses

import android.util.Log
import com.lesa.burunduk.data.expenses.models.Category
import com.lesa.burunduk.data.expenses.models.Expense
import com.lesa.burunduk.data.expenses.storage.DBCategory
import com.lesa.burunduk.data.expenses.storage.DBExpense
import com.lesa.burunduk.data.expenses.storage.ExpenseDao
import com.lesa.burunduk.ui.screens.expenseEntry.ExpenseDetails
import com.lesa.burunduk.ui.screens.expenseEntry.toExpenseDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.UUID

class OfflineExpensesRepository(private val expenseDao: ExpenseDao): ExpensesRepository {

    override fun getAllExpensesStream(): Flow<List<Expense>> {
        return expenseDao.getAllExpensesOrderByDateDesc().map { dbExpenseList ->
            dbExpenseList.map {
                it.toExpense()
            }
        }
    }

    override fun getSumPerDay(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun insertExpense(expense: Expense) =
        expenseDao.insertExpense(expense.toDBExpense())

    override suspend fun deleteExpense(id: UUID) =
        expenseDao.deleteExpense(id)

    override suspend fun findExpenseById(id: UUID): ExpenseDetails {
        return expenseDao.findExpenseById(id).toExpense().toExpenseDetails()
    }

    /*override suspend fun updateExpense(expense: Expense) =
        expenseDao.updateExpense(expense.toDBExpense())*/
}

private fun Expense.toDBExpense(): DBExpense {
    val date = date.atOffset(ZoneOffset.UTC).toEpochSecond()
    return DBExpense(
        id = id,
        date = date,
        category = category.toDBCategory(),
        local = local,
        exchangeRate = exchangeRate,
        kopecks = kopecks
    )
}

private fun DBExpense.toExpense(): Expense {
    val date = LocalDateTime.ofEpochSecond(date, 0, ZoneOffset.UTC)
    return Expense(
        id = id,
        date = date,
        category = category.toCategory(),
        local = local,
        exchangeRate = exchangeRate,
        kopecks = kopecks
    )
}

private fun Category.toDBCategory(): DBCategory {
    return when(this) {
        Category.ALL_FOOD -> DBCategory.ALL_FOOD
        Category.VEGAN_FOOD -> DBCategory.VEGAN_FOOD
        Category.HOUSEWARES -> DBCategory.HOUSEWARES
        Category.HYGIENE -> DBCategory.HYGIENE
        Category.HEALTH -> DBCategory.HEALTH
        Category.PREPARED_FOOD -> DBCategory.PREPARED_FOOD
        Category.ENTERTAINMENT -> DBCategory.ENTERTAINMENT
        Category.BIG_VACATION -> DBCategory.BIG_VACATION
        Category.RENT -> DBCategory.RENT
        Category.COMMUNICATIONS -> DBCategory.COMMUNICATIONS
        Category.CLOTHES -> DBCategory.CLOTHES
        Category.ELECTRONICS -> DBCategory.ELECTRONICS
        Category.TRANSPORT -> DBCategory.TRANSPORT
        Category.OTHER -> DBCategory.OTHER
    }
}

private fun DBCategory.toCategory(): Category {
    return when(this) {
        DBCategory.ALL_FOOD -> Category.ALL_FOOD
        DBCategory.VEGAN_FOOD -> Category.VEGAN_FOOD
        DBCategory.HOUSEWARES -> Category.HOUSEWARES
        DBCategory.HYGIENE -> Category.HYGIENE
        DBCategory.HEALTH -> Category.HEALTH
        DBCategory.PREPARED_FOOD -> Category.PREPARED_FOOD
        DBCategory.ENTERTAINMENT -> Category.ENTERTAINMENT
        DBCategory.BIG_VACATION -> Category.BIG_VACATION
        DBCategory.RENT -> Category.RENT
        DBCategory.COMMUNICATIONS -> Category.COMMUNICATIONS
        DBCategory.CLOTHES -> Category.CLOTHES
        DBCategory.ELECTRONICS -> Category.ELECTRONICS
        DBCategory.TRANSPORT -> Category.TRANSPORT
        DBCategory.OTHER -> Category.OTHER
        DBCategory.UNKNOWN -> {
            Log.d("Data", "unknown category")
            return Category.OTHER
        }
    }
}