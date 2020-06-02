package com.sensyne.hospitals.utils

import com.sensyne.hospitals.model.Hospitals
import java.io.BufferedReader

object CSVParser {

    fun parse(reader: BufferedReader) : Hospitals {
        val data = mutableListOf<List<String>>()
        val iterator= reader.lineSequence().iterator()
        val headers = iterator.next().split('�')
        while(iterator.hasNext()) {
            val row = iterator.next().trim().split('�')
            data.add(row)
        }

        return Hospitals(headers, data)
    }
}