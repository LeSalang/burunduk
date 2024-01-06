package com.lesa.burunduk.dependencies.shoppingList

import android.app.Application
import androidx.room.Room
import com.lesa.burunduk.data.shoppingList.ShoppingListRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShoppingListModule {

    @Provides
    @Singleton
    fun provideShoppingListDB(app: Application): ShoppingListRoomDB {
        return Room.databaseBuilder(
            app,
            ShoppingListRoomDB::class.java,
            "shopping_list_table"
        ).build()
    }
}