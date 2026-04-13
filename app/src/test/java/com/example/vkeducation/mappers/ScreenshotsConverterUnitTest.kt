package com.example.vkeducation.mappers

import com.example.vkeducation.data.local.ScreenshotsConverter
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ScreenshotsConverterUnitTest {

    @Test
    fun `converting string like -,-,- to list of strings is correct`() {

        val string = "oleg,mongol"

        val list = ScreenshotsConverter().toStringList(string)

        assertEquals(listOf("oleg", "mongol"), list)
    }

    @Test
    fun `converting empty string to empty list is correct`() {

        val string = ""

        val list = ScreenshotsConverter().toStringList(string)

        assertEquals(emptyList<String>(), list)
    }

    @Test
    fun `converting list of strings to string like -,-,- is correct`() {

        val list = listOf("oleg", "mongol")

        val string = ScreenshotsConverter().fromStringList(list)

        assertEquals("oleg,mongol", string)
    }

    @Test
    fun `converting empty list to empty string is correct`() {

        val list = emptyList<String>()

        val string = ScreenshotsConverter().fromStringList(list)

        assertEquals("", string)
    }



    }