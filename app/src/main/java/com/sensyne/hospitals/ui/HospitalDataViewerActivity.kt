package com.sensyne.hospitals.ui

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sensyne.hospitals.R
import kotlinx.android.synthetic.main.activity_hospital_data_viewer.*


class HospitalDataViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_hospital_data_viewer)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val headers: Array<out String>? = intent.extras?.getStringArray("headers")
        val data: Array<out String>? = intent.extras?.getStringArray("data")

        populateData(headers!!, data!!)
    }

    fun populateData(headers: Array<out String>, dataRow: Array<out String>) {
        for (index in 0..headers.size - 1) {
            val headerView = getHeaderView(headers[index], this)
            val dataView = getDataView(dataRow[index], this)

            hospitalData.addView(headerView)
            hospitalData.addView(dataView)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()

        return true
    }

    fun getHeaderView(header: String, context: Context) : TextView {
        val textView = TextView(context)
        textView.text = header
        textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
        textView.textSize = 18.0f
        textView.setPadding(16, 8, 64, 4)
        textView.setTextColor(context.resources.getColor(android.R.color.black))

        return textView
    }

    fun getDataView(data: String, context: Context) : TextView {
        val textView = TextView(context)
        textView.text = if (data.isEmpty()) "Not available" else data
        textView.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)
        textView.textSize = 15.0f
        textView.setPadding(16, 4, 64, 16)
        textView.setTextColor(context.resources.getColor(android.R.color.black))

        return textView
    }
}