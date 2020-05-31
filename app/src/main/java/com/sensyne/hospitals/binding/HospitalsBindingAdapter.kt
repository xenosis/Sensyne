package com.sensyne.hospitals.binding

import android.content.Context
import android.graphics.Typeface
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sensyne.hospitals.model.Hospitals

@BindingAdapter(value = ["app:hospitals"], requireAll = true)
fun setHospitalData(tableLayout: TableLayout, hospitals: Hospitals?) {
    if (hospitals != null) populateData(tableLayout, hospitals)
}

fun populateData(tableLayout: TableLayout, hospitals: Hospitals) {
    val context = tableLayout.context
    val tableRow = getHeaderRow(context)

    hospitals.headers.forEach { header ->
        tableRow.addView(getHeaderView(header, tableLayout.context))
    }


    tableLayout.addView(tableRow)

    hospitals.data.forEach { data ->
        val tableRow: TableRow = getDataRow(context)

        val iterator = data.listIterator()
        while(iterator.hasNext()) {
            val dataRow = iterator.next().trim()
            tableRow.addView(getDataView(dataRow, context))
        }

        tableLayout.addView(tableRow)
    }
}

fun getDataRow(context: Context) : TableRow {
    val tableRow = TableRow(context)

    val tableRowParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT)
    tableRowParams.setMargins(1, 1, 1, 1)
    tableRow.layoutParams = tableRowParams

    return tableRow
}

fun getHeaderRow(context: Context) : TableRow {
    val tableRow = TableRow(context)
    tableRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)

    return tableRow
}

fun getHeaderView(header: String, context: Context) : TextView {
    val textViewId = TextView(context)
    textViewId.text = header
    textViewId.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
    textViewId.setPadding(32, 0, 64, 0)

    return textViewId
}

fun getDataView(header: String, context: Context) : TextView {
    val textViewId = TextView(context)
    textViewId.text = header
    textViewId.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
    textViewId.setPadding(32, 0, 0, 0)

    return textViewId
}