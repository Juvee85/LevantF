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
        for (i in 0..360 step paso) {
            if (i <= 180) valoresTheta.add(calculadora.calcularDesplazamiento(i.aRadianes(),
                25.toDouble(), 180.aRadianes(), 0.toDouble()))
            if (i > 180) {
                calculadora = CalculadoraBajada345()
                valoresTheta.add(calculadora.calcularDesplazamiento((i-180).aRadianes(),
                    25.toDouble(), 180.aRadianes(), 25.toDouble()))
            }
        }

        val perfil =
            PerfilGraphActivity().calcularPerfil(valoresTheta, paso, 13.toDouble())

        assertEquals(10.5618323901341, perfil[475].first, 0.00001)
        assertEquals(11.5262174742221, perfil[475].second, 0.00001)

        assertEquals(-20.7579250295182, perfil[2326].first, 0.00001)
        assertEquals(-27.15023887506, perfil[2326].second, 0.00001)
    }
}