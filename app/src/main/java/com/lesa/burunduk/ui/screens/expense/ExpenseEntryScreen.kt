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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lesa.burunduk.R
import com.lesa.burunduk.data.expenses.models.Category
import com.lesa.burunduk.data.expenses.models.nameId
import com.lesa.burunduk.ui.AppViewModelProvider
import com.lesa.burunduk.ui.components.MyText
import com.lesa.burunduk.ui.theme.BlackBlue
import com.lesa.burunduk.ui.theme.Red
import com.lesa.burunduk.ui.theme.WhiteBlue
import com.lesa.burunduk.ui.theme.WhiteRed
import kotlinx.coroutines.launch

@Composable
fun ExpenseEntryScreen(
    viewModel: ExpenseEntryViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateBack: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    Column (
        Modifier
            .padding(
                start = 10.dp,
                end = 10.dp
            )
            .background(WhiteBlue)
    ){
        ExpenseInputForm(
            expenseDetails = viewModel.expenseUiState.expenseDetails,
            onValueChange = viewModel :: updateUiState,
        )
        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.saveExpense()
                    navigateBack()
                }
            },
            enabled = viewModel.expenseUiState.isEntryValid
        ) {
            Text(text = "Save data")
        }
    }
}

@Composable
fun ExpenseInputForm(
    expenseDetails: ExpenseDetails,
    onValueChange: (ExpenseDetails) -> Unit = {},
) {
    Spacer(modifier = Modifier.size(10.dp))
    Card(
        colors = CardDefaults.cardColors(containerColor = WhiteRed),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            Modifier.padding(10.dp)
        ) {
            SelectCatRadioButtons(
                expenseDetails = expenseDetails,
                onValueChange = onValueChange
            )
            ExpenseTextField(
                expenseDetails = expenseDetails,
                onValueChange = onValueChange
            )
        }
    }
}

@Composable
fun ExpenseTextField(
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
                focusedContainerColor = WhiteBlue,
                unfocusedContainerColor = WhiteBlue,
                unfocusedIndicatorColor = WhiteBlue
            ),
            textStyle = TextStyle(
                color = Red,
                fontSize = 20.sp,
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
            modifier = Modifier
                .weight(0.5f),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = WhiteBlue,
                unfocusedContainerColor = WhiteBlue,
                unfocusedIndicatorColor = WhiteBlue
            ),
            textStyle = TextStyle(
                color = Red,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun SelectCatRadioButtons(
    expenseDetails: ExpenseDetails,
    onValueChange: (ExpenseDetails) -> Unit = {}
) {
    val radioOptions = stringArrayResource(id = R.array.categories)
    val dBCategoryList = Category.values()
    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(dBCategoryList[dBCategoryList.lastIndex] )
    }
    Column(
        modifier = Modifier.padding( 8.dp)
    ) {
        MyText(text = stringResource(id = R.string.purchase_category))
        Spacer(modifier = Modifier.padding(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(dBCategoryList) { category ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (category == expenseDetails.category),
                            onClick = {
                                onValueChange(expenseDetails.copy(category = category))
                                onOptionSelected(category)
                            }
                        )
                        .padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (category == expenseDetails.category),
                        onClick = {
                            onValueChange(expenseDetails.copy(category = category))
                            onOptionSelected(category)
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Red,
                            unselectedColor = BlackBlue
                        ),
                        modifier = Modifier
                            .size(40.dp)
                    )
                    MyText(
                        text = stringResource(id = category.nameId),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 4.dp),
                        maxLines = 1)
                }
            }
        }
    }
}
