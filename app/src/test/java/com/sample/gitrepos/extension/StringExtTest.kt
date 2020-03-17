package com.sample.gitrepos.extension

import com.sample.gitrepos.extensions.filterNull
import com.sample.gitrepos.extensions.toSafeString
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

class StringExtTest {

    @Test
    fun `validate toSafeString extension`() {
        val validString = "Trending"
        val nullableString: String? = null
        val safeString  = ""

        //When string is valid
        assertEquals(validString, validString.filterNull())
        //When string is null with no default value, empty string is returned
        assertEquals(safeString, nullableString.filterNull())
        //When string is null with default value, must return default value
        assertEquals(validString, nullableString.filterNull(validString))
        //When extension is chained with toString() string function
        assertEquals("1", 1.toString().filterNull())
    }
}
