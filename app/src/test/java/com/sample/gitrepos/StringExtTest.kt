package com.sample.gitrepos

import com.sample.gitrepos.extensions.toSafeString
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

class StringExtTest {

    @Test
    fun `validate toSafeString extension`() {
        val validString = "1"
        val validInt = 1
        val negInt = -1
        val nullableInt: Int? = null
        val safeString  = ""

        assertEquals(validString, validInt.toSafeString())
        assertEquals(safeString, nullableInt.toSafeString())
        assertEquals(negInt.toString(), negInt.toSafeString())
    }
}
