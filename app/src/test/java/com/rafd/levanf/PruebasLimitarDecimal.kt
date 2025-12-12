package com.rafd.levanf

import android.text.SpannableStringBuilder
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import utils.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PruebasLimitarDecimal {

    @Test
    fun limitarDecimalA2() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val et = EditText(context)

        et.limitarDecimal(5, 2)

        assertEquals(1, et.filters.size)

        val filter = et.filters[0]

        val permitido = filter.filter("1.03", 0, 4, SpannableStringBuilder(""), 0, 0)
        assertEquals(null, permitido)

        val bloqueado = filter.filter("1234.998757", 0, 11, SpannableStringBuilder(""), 0, 0)
        assertEquals("", bloqueado)
    }

    @Test
    fun limitarDigitosAntesDeDecimal() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val et = EditText(context)

        et.limitarDecimal(2, 12)

        val filter = et.filters[0]

        val permitido = filter.filter("10.3", 0, 4, SpannableStringBuilder(""), 0, 0)
        assertEquals(null, permitido)

        val bloqueado = filter.filter("123499.8757", 0, 11, SpannableStringBuilder(""), 0, 0)
        assertEquals("", bloqueado)
    }
}