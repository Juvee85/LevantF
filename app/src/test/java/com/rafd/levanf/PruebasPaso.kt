package com.rafd.levanf

import org.junit.Test
import utils.*
import org.junit.Assert.*

class PruebasPaso {

    @Test
    fun pasoDecimal() {
        val rango = (1..5) step 0.1
        var esperado = 1.0
        for (resultado in rango) {
            assert(resultado == esperado)
            esperado = esperado + 0.1
        }
    }

    @Test
    fun pasoNoSaleDeRango() {
        var rango = (0..360) step 0.1
        assert(rango.last() <= 360)

        rango = (0.. 10) step 4.5
        assert(rango.last() <= 10)
    }

    @Test
    fun pasoEnRangoInicioNegativo() {
        val rango = (-4..7) step 0.5
        var esperado = -4.0
        for (resultado in rango) {
            assert(resultado == esperado)
            esperado = esperado + 0.5
        }
    }

    @Test
    fun pasoRangoInvertido() {
        val rango = (360..0) step 0.1
        assert(rango.count() == 0)
    }

    @Test
    fun excepcionPaso0() {
        assertThrows(IllegalArgumentException::class.java) {
            (1..5) step 0.0
        }
    }

    @Test
    fun excepcionPasoNegativo () {
        assertThrows(IllegalArgumentException::class.java) {
            (1..5) step -1.0
        }
    }
}