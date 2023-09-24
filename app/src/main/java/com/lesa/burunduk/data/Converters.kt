package com.lesa.burunduk.data

import androidx.room.TypeConverter
import com.lesa.burunduk.data.expenses.storage.DBCategory

class Converters {

    @TypeConverter
    fun dbCategoryToInt(dbCategory: DBCategory): Int {
        return dbCategory.id
    }

    @TypeConverter
    fun intToDBCategory(id: Int): DBCategory {
        return DBCategory.values().firstOrNull{ id == it.id } ?: DBCategory.UNKNOWN
    }
}