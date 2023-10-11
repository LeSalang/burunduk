package com.lesa.burunduk.ui.screens.stats

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.lesa.burunduk.ui.screens.home.HomeUiState
import com.lesa.burunduk.ui.screens.home.getListOfPoints
import com.lesa.burunduk.ui.theme.Blue
import com.lesa.burunduk.ui.theme.Red
import com.lesa.burunduk.ui.theme.WhiteRed
import kotlin.math.roundToInt
private const val steps = 10
@Composable
fun MyLineChart(
    homeUiState: HomeUiState,
    selectedMonth: MutableState<String>
) {
    val chosenMonth = Month.valueOf(selectedMonth.value).ordinal
    Log.d("MyLog", "$chosenMonth")
    val listOfPoints = homeUiState.getListOfPoints(2023, chosenMonth + 1)
    val max = getMaxSum(listOfPoints)

    val xAxisData = AxisData.Builder()
        .axisStepSize(20.dp)
        .backgroundColor(WhiteRed)
        .steps(listOfPoints.size - 1)
        .labelData { i ->
            (i + 1).toString()
        }
        .labelAndAxisLinePadding(10.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(WhiteRed)
        .labelAndAxisLinePadding(15.dp)
        .labelData { i ->
            val yScale = (max / steps).roundToInt()
            (i * yScale).toString() + " \u20BD"
        }.build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = listOfPoints,
                    LineStyle(color = Red, width = 5f),
                    IntersectionPoint(radius = 4.dp),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(color = Blue),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = WhiteRed
    )
    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = lineChartData
    )
}

private fun getMaxSum(list: ArrayList<Point>): Float {
    var max = 0f
    list.forEach {
        if (max < it.y) max = it.y
    }
    return max
}