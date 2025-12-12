package com.rafd.levanf

import org.junit.Test
import utils.*
import org.junit.Assert.*
import kotlin.math.PI

class PruebasConversiones {

    val tolerancia = 0.00001

    @Test
    fun rpmARadianesSegundos() {
        val resultado = 400.00.aRadianesSegundos()
        val esperado = 41.88790

        assertEquals(esperado, resultado, tolerancia)
    }

    @Test
    fun rpmARadianesSegundosPI() {
        val resultado = 120.00.aRadianesSegundos()
        val esperado = 4 * PI

        assertEquals(esperado, resultado, tolerancia)
    }

    @Test
    fun gradosARadianesInt() {
        val resultado = 360.aRadianes()
        val esperado = 2 * PI

        assertEquals(esperado, resultado, tolerancia)
    }

    @Test
    fun gradosARadianesDouble() {
        val resultado = 145.62.aRadianes()
        val esperado = 2.54154

        assertEquals(esperado, resultado, tolerancia)
    }

    @Test
    fun conversionesDeVuelta() {
        var resultado = 203.75.aRadianes().aGrados()
        var esperado = 203.75

        assertEquals(esperado, resultado, tolerancia)

        resultado = 180.aRadianes().aGrados()
        esperado = 180.00

        assertEquals(esperado, resultado, tolerancia)

        resultado = (2*PI).aGrados().aRadianes()
        esperado = 2*PI

        assertEquals(esperado, resultado, tolerancia)
    }
}