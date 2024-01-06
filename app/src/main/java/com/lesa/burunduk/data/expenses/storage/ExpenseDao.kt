package com.lesa.burunduk.data.expenses.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense_table ORDER BY date DESC")
    fun getAllExpensesOrderByDateDesc(): Flow<List<DBExpense>>

    @Query("SELECT * FROM expense_table ORDER BY date ASC")
    fun getAllExpensesOrderByDateAsc(): Flow<List<DBExpense>>

    @Query("SELECT * FROM expense_table ORDER BY local ASC")
    fun getAllExpensesOrderByAzn(): Flow<List<DBExpense>>

    @Query("SELECT * FROM expense_table WHERE date = :day")
    fun getSumPerDay(day: Long): Flow<DBExpense>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: DBExpense)

   /* @Update
    suspend fun updateExpense(expense: DBExpense)
*/
    @Query("DELETE FROM expense_table WHERE id = :id")
    suspend fun deleteExpense(id: UUID)

    @Query("SELECT * FROM expense_table WHERE id = :id")
    suspend fun findExpenseById(id: UUID): DBExpense


    @Query("DELETE FROM expense_table")
    suspend fun deleteAllExpenses()

   

}