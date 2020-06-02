package com.sensyne.hospitals

import com.sensyne.hospitals.model.Hospitals
import com.sensyne.hospitals.utils.CSVParser
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.BufferedReader
import java.io.Reader
import java.io.StringReader

class HospitalsUnitTest {

    lateinit var hospitals: Hospitals

    @Before
    fun setup() {
        val csvData =
        """
            OrganisationID�OrganisationCode�OrganisationType
            17970�NDA07�Hospital
            17981�NDA18�Hospital
            18102�NLT02�Hospital
        """.trim()

        val reader: Reader = StringReader(csvData)
        val bufferedReader = BufferedReader(reader)
        hospitals = CSVParser.parse(bufferedReader)
    }


    @Test
    fun testCSVParsingHeaders() {
        assertEquals(hospitals.headers[0], "OrganisationID")
        assertEquals(hospitals.headers[1], "OrganisationCode")
        assertEquals(hospitals.headers[2], "OrganisationType")
    }

    @Test
    fun testCSVParsingData() {
        assertEquals(hospitals.data[0][0], "17970")
        assertEquals(hospitals.data[0][1], "NDA07")
        assertEquals(hospitals.data[0][2], "Hospital")

        assertEquals(hospitals.data[1][0], "17981")
        assertEquals(hospitals.data[1][1], "NDA18")
        assertEquals(hospitals.data[1][2], "Hospital")

        assertEquals(hospitals.data[2][0], "18102")
        assertEquals(hospitals.data[2][1], "NLT02")
        assertEquals(hospitals.data[2][2], "Hospital")
    }
}
