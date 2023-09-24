package com.lesa.burunduk.data.expenses.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "expense_table")
data class DBExpense(
    @PrimaryKey
    val id: UUID,
    val date: Long,
    val category: DBCategory,
    val local: Int,
    @ColumnInfo(name = "exchange_rate")
    val exchangeRate: Double,
    val kopecks: Int
)