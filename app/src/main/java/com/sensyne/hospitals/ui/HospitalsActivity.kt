package com.sensyne.hospitals.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sensyne.hospitals.R
import com.sensyne.hospitals.model.Hospitals
import com.sensyne.hospitals.network.ApiClient
import kotlinx.android.synthetic.main.activity_main.*

class HospitalsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = ApiClient
        client.getHospitalInformation({ hospitals ->
            populateData(hospitals)
        }, { networkError ->
            Toast.makeText(this, "Error retrieving hospital data", Toast.LENGTH_LONG).show()
        })
    }

    fun populateData(hospitals: Hospitals) {
        val tableRow = getHeaderRow()

        hospitals.headers.forEach { header ->
            tableRow.addView(getHeaderView(header))
        }


        tableLayout.addView(tableRow)

        hospitals.data.forEach { data ->
            val tableRow: TableRow = getDataRow()

            val iterator = data.listIterator()
            while(iterator.hasNext()) {
                val dataRow = iterator.next().trim()
                tableRow.addView(getDataView(dataRow))
            }

            tableLayout.addView(tableRow)
        }

        progressBar.visibility = View.GONE
        tableLayout.visibility = View.VISIBLE
    }

    fun getDataRow() : TableRow {
        val tableRow = TableRow(this)

        val tableRowParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT)
        tableRowParams.setMargins(1, 1, 1, 1)
        tableRow.layoutParams = tableRowParams

        return tableRow
    }

    fun getHeaderRow() : TableRow {
        val tableRow = TableRow(this)
        tableRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)

        return tableRow
    }

    fun getHeaderView(header: String) : TextView {
        val textViewId = TextView(this)
        textViewId.text = header
        textViewId.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
        textViewId.setPadding(32, 0, 64, 0)

        return textViewId
    }

    fun getDataView(header: String) : TextView {
        val textViewId = TextView(this)
        textViewId.text = header
        textViewId.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
        textViewId.setPadding(32, 0, 0, 0)

        return textViewId
    }
}
