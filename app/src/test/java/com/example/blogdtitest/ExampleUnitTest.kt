package com.example.blogdtitest

import com.example.blogdtitest.util.Utils
import org.junit.Test

import org.junit.Assert.*
import java.util.Date

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun dateFormatCorrect() {
        val date = Utils.getHour(Date())
        assertEquals(2, date.count { it == '-' })
    }
}