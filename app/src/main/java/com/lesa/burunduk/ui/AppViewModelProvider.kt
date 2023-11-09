package com.lesa.burunduk.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.lesa.burunduk.ExpenseApplication
import com.lesa.burunduk.ui.screens.expense.ExpenseEntryViewModel
import com.lesa.burunduk.ui.screens.home.HomeViewModel
import com.lesa.burunduk.ui.screens.stats.StatsViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ExpenseEntryViewModel(expensesApplication().container.expensesRepository)
        }

        initializer {
            HomeViewModel(expensesApplication().container.expensesRepository)
        }

        initializer {
            StatsViewModel(
                expensesApplication().container.expensesRepository
            )
        }
    }
}

fun CreationExtras.expensesApplication(): ExpenseApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ExpenseApplication)