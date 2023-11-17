package com.lesa.burunduk.ui.screens.stats

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
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
import com.lesa.burunduk.ui.theme.LeSaTheme
import kotlin.math.roundToInt

class StatsLineChartState(var points: ArrayList<Point>) {
    val maxY: Float = points.maxOf { it.y }
}

private const val STEPS = 10

@Composable
fun StatsLineChart(
    state: StatsLineChartState
) {
    val max = state.maxY

    val xAxisData = AxisData.Builder()
        .axisStepSize(20.dp)
        .backgroundColor(LeSaTheme.colors.background80)
        .steps(state.points.size - 1)
        .labelData { i ->
            (i + 1).toString()
        }
        .labelAndAxisLinePadding(10.dp)
        .axisLineColor(LeSaTheme.colors.text)
        .axisLabelColor(LeSaTheme.colors.text)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(STEPS)
        .backgroundColor(LeSaTheme.colors.background80)
        .labelAndAxisLinePadding(15.dp)
        .labelData { i ->
            val yScale = (max / STEPS)
            (i * yScale).roundToInt().toString() + " \u20BD"
        }
        .axisLineColor(LeSaTheme.colors.text)
        .axisLabelColor(LeSaTheme.colors.text)
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = state.points,
                    LineStyle(color = LeSaTheme.colors.primary, width = 5f),
                    IntersectionPoint(
                        radius = 3.dp,
                        color = LeSaTheme.colors.text
                    ),
                    SelectionHighlightPoint(
                        radius = 5.dp,
                        color = LeSaTheme.colors.primary
                    ),
                    ShadowUnderLine(color = LeSaTheme.colors.base),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = LeSaTheme.colors.background80
    )
   /* if (state.points.isEmpty())
        Text(text = "list is empty")
    else*/
    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = lineChartData
    )
}

