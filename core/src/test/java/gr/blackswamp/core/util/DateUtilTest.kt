package gr.blackswamp.core.util

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class DateUtilTest {

    @Before
    fun setUp(){
        formatters.clear()
    }

    @Test
    fun `test toDateTimeString parses correctly`() {
        val date = Calendar.getInstance().apply {
            set(2019, 11, 11, 23, 45, 24)
        }.time
        val expected = "2019-12-11 23:45:24"
        val received = date.toDateTimeString()
        assertEquals(expected, received)
    }

    @Test
    fun `test no new formatters are created`() {
        val date = Calendar.getInstance().time

        date.asString("yyyy-MM-dd")
        date.asString("yyyy-MM-dd")
        date.asString("yyyy-MM-dd MM MM")

        assertEquals(2, formatters.size)
    }

    @Test
    fun `test mixed formatters work`() {
        val date = Calendar.getInstance().apply {
            set(2019, 11, 11, 23, 45, 24)
            set(Calendar.MILLISECOND, 123)
        }.time

        val response = date.asString("yyyy-MM-dd MM MM yyyy mm s ss SSSSSS")
        val expected = "2019-12-11 12 12 2019 45 24 24 000123"
        assertEquals(1, formatters.size)
        assertEquals(expected,response)

    }

}