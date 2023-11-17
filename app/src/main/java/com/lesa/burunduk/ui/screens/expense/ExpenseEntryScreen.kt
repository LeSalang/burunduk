package com.lesa.burunduk.ui.screens.expense

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lesa.burunduk.R
import com.lesa.burunduk.data.expenses.models.Category
import com.lesa.burunduk.data.expenses.models.nameId
import com.lesa.burunduk.ui.AppViewModelProvider
import com.lesa.burunduk.ui.components.MyText
import com.lesa.burunduk.ui.screens.FABConfigurator
import com.lesa.burunduk.ui.theme.LeSaTheme
import kotlinx.coroutines.launch

@Composable
fun ExpenseEntryScreen(
    viewModel: ExpenseEntryViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateBack: () -> Unit,
    setFABConfigurator: ((FABConfigurator?) -> Unit)
) {
    val coroutineScope = rememberCoroutineScope()
    setFABConfigurator.invoke {
        coroutineScope.launch {
            viewModel.validateInput()
            if (viewModel.expenseUiState.validationResult.isValid) {
                viewModel.saveExpense()
                navigateBack()
            }
        }
    }
   DisposableEffect(Unit) {
       onDispose {
           setFABConfigurator(null)
       }
   }
    Column (
        Modifier
            .padding(
                start = 10.dp,
                end = 10.dp
            )
            .background(LeSaTheme.colors.background80)
    ){
        ExpenseInputForm(
            expenseDetails = viewModel.expenseUiState.expenseDetails,
            onValueChange = viewModel :: updateUiState
        )
    }
}

@Composable
private fun ExpenseInputForm(
    expenseDetails: ExpenseDetails,
    onValueChange: (ExpenseDetails) -> Unit = {}
) {
    Spacer(modifier = Modifier.size(10.dp))
    Card(
        colors = CardDefaults.cardColors(containerColor = LeSaTheme.colors.background80),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            Modifier.padding(10.dp)
        ) {
            SelectCatRadioButtons(
                expenseDetails = expenseDetails,
                onValueChange = onValueChange,
            )
            ExpenseTextField(
                expenseDetails = expenseDetails,
                onValueChange = onValueChange
            )
        }
    }
}

@Composable
private fun ExpenseTextField(
    expenseDetails: ExpenseDetails,
    onValueChange: (ExpenseDetails) -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding( 8.dp)
    ) {
        MyText(
            text = stringResource(id = R.string.price_in_local_currency),
            Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.weight(0.1f))
        TextField(
            value = expenseDetails.local,
            onValueChange = {
                onValueChange(expenseDetails.copy(local = it))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .weight(0.5f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LeSaTheme.colors.background90,
                unfocusedContainerColor = LeSaTheme.colors.background90,
                unfocusedIndicatorColor = LeSaTheme.colors.background90
            ),
            textStyle = TextStyle(
                color = LeSaTheme.colors.primary,
                fontSize = LeSaTheme.typography.hat.fontSize,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        )
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding( 8.dp)
    ) {
        MyText(
            text = stringResource(id = R.string.local_exchange_rate),
            Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.weight(0.1f))
        TextField(
            value = expenseDetails.exchangeRate,
            onValueChange = {
                onValueChange(expenseDetails.copy(exchangeRate = it))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            modifier = Modifier
                .weight(0.5f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LeSaTheme.colors.background90,
                unfocusedContainerColor = LeSaTheme.colors.background90,
                unfocusedIndicatorColor = LeSaTheme.colors.background90
            ),
            textStyle = TextStyle(
                color = LeSaTheme.colors.primary,
                fontSize = LeSaTheme.typography.hat.fontSize,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
private fun SelectCatRadioButtons(
    expenseDetails: ExpenseDetails,
    onValueChange: (ExpenseDetails) -> Unit = {},
) {
    val allCategories = Category.values()
    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(expenseDetails.category)
    }
    Column(
        modifier = Modifier.padding( 8.dp)
    ) {
        MyText(text = stringResource(id = R.string.purchase_category))
        Spacer(modifier = Modifier.padding(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(allCategories) { category ->
                val isSelected = category == selectedOption
                val onClick = {
                    onValueChange(expenseDetails.copy(category = category))
                    onOptionSelected(category)
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = isSelected,
                            onClick = onClick
                        )
                        .padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isSelected,
                        onClick = onClick,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = LeSaTheme.colors.primary,
                            unselectedColor = LeSaTheme.colors.text
                        ),
                        modifier = Modifier
                            .size(40.dp)
                    )
                    MyText(
                        text = stringResource(id = category.nameId),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = LeSaTheme.shape.padding),
                        maxLines = 1
                    )
                }
            }
        }
    }
}
