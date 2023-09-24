package com.lesa.burunduk.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lesa.burunduk.data.expenses.storage.DBExpense
import com.lesa.burunduk.data.expenses.storage.ExpenseDao

@Database(
    entities = [DBExpense::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ExpenseRoomDataBase: RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: ExpenseRoomDataBase? = null
        fun getDataBase(
            context: Context
        ): ExpenseRoomDataBase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, ExpenseRoomDataBase::class.java, "expense_table")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}