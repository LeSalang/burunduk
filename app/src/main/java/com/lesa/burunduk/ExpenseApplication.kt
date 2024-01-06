package com.lesa.burunduk

import android.app.Application
import com.lesa.burunduk.data.AppContainer
import com.lesa.burunduk.data.AppDataContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ExpenseApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}