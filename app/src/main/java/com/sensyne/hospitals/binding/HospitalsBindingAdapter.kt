package com.sensyne.hospitals.binding

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sensyne.hospitals.R
import com.sensyne.hospitals.model.Hospitals
import com.sensyne.hospitals.ui.HospitalDataViewerActivity

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
    hospitals.data.forEachIndexed{ index, data ->
        val tableRow: TableRow = getDataRow(context)
        tableRow.id = index
        val rowOddColour =  R.color.lightBlue
        val rowEvenColour = android.R.color.white
        if (index % 2 == 0) {
            tableRow.setBackgroundResource(rowEvenColour)
        } else {
            tableRow.setBackgroundResource(rowOddColour)
        }
        val iterator = data.listIterator()
        while(iterator.hasNext()) {
            val dataRow = iterator.next().trim()
            tableRow.addView(getDataView(dataRow, context))
        }

        tableRow.setOnClickListener { view ->
            val tableRow = view as TableRow
            val id = tableRow.id
            val headers = hospitals.headers
            val rowData = hospitals.data[index]
            val context = tableRow.context
            val intent = Intent(context, HospitalDataViewerActivity::class.java)
            val bundle = Bundle()
            bundle.putStringArray("headers", headers.toTypedArray())
            bundle.putStringArray("data", rowData.toTypedArray())
            intent.putExtras(bundle)
            context.startActivity(intent)
        }

        tableLayout.addView(tableRow)
    }
}

fun getDataRow(context: Context) : TableRow {
    val tableRow = TableRow(context)
    tableRow.dividerDrawable = ColorDrawable(0xFF000000.toInt())
    tableRow.showDividers = TableRow.SHOW_DIVIDER_MIDDLE

    val tableRowParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT)
    tableRow.layoutParams = tableRowParams

    return tableRow
}

fun getHeaderRow(context: Context) : TableRow {
    val tableRow = TableRow(context)

    val tableRowParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT)
    tableRow.layoutParams = tableRowParams

    tableRow.setBackgroundResource(R.color.colorPrimaryDark)

    return tableRow
}

fun getHeaderView(header: String, context: Context) : TextView {
    val textView = TextView(context)
    textView.text = header
    textView.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)
    textView.textSize = 17.0f
    textView.setPadding(16, 16, 64, 16)
    textView.setTextColor(context.resources.getColor(android.R.color.white))

    return textView
}

fun getDataView(dataRow: String, context: Context) : TextView {
    val textView = TextView(context)
    textView.text = dataRow
    textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
    textView.textSize = 15.0f
    textView.setPadding(16, 16, 64, 16)
    textView.setTextColor(context.resources.getColor(android.R.color.black))

    return textView
}