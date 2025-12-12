package com.rafd.levanf

import org.junit.Test
import utils.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import svaj.CalculadoraBajada345
import svaj.CalculadoraSVAJ
import svaj.CalculadoraSubidaCicloidal

@RunWith(RobolectricTestRunner::class)
class PruebasPerfil {

    @Test
    fun pruebaGenerarPerfilPaso1Grado() {
        val paso = 0.1
        val valoresTheta = ArrayList<Double>()

        var calculadora: CalculadoraSVAJ = CalculadoraSubidaCicloidal()
        for (i in 0..180 step paso){
            valoresTheta.add(calculadora.calcularDesplazamiento(i.aRadianes(),
                25.toDouble(), 180.aRadianes(), 0.toDouble()))
        }

        calculadora = CalculadoraBajada345()
        for (i in 0..180 step paso){
            valoresTheta.add(calculadora.calcularDesplazamiento((i+0.1).aRadianes(),
                25.toDouble(), 180.aRadianes(), 25.toDouble()))
        }

        println("Tamaño total: ${valoresTheta.size}")
        for (i in 1798..1805) {
            if (i < valoresTheta.size) {
                println("Índice $i: ${valoresTheta[i]}")
            }
        }

        val perfil =
            PerfilGraphActivity().calcularPerfil(valoresTheta, paso, 13.toDouble())

        assertEquals(10.5618323901341, perfil[475].first, 0.00001)
        assertEquals(11.5262174742221, perfil[475].second, 0.00001)

        assertEquals(-20.7579250295182, perfil[2326].first, 0.00001)
        assertEquals(-27.1722324531943, perfil[2326].second, 0.00001)
    }
}