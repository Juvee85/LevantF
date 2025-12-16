package com.rafd.levanf

import org.junit.Test
import utils.*
import org.junit.Assert.*
import svaj.CalculadoraBajada345
import svaj.CalculadoraBajada4567
import svaj.CalculadoraDetenimientoAlto
import svaj.CalculadoraDetenimientoBajo
import svaj.CalculadoraSVAJ
import svaj.CalculadoraSubida345
import svaj.CalculadoraSubidaCicloidal
import svaj.CalculadoraSubidaMAS
import svaj.GeneradorPerfil

/**
 * Pruebas ára la generación de perfil, basadas en los archivos de prueba
 */
class PruebasPerfil {

    @Test
    fun `Generar perfil prueba 1`() {
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
            GeneradorPerfil().calcularPerfil(valoresTheta, paso, 13.toDouble())

        // Prueba en el primer segmento
        assertEquals(10.5618323901341, perfil[475].first, 0.00001)
        assertEquals(11.5262174742221, perfil[475].second, 0.00001)

        // Prueba en el segundo segmento
        assertEquals(-20.7579250295182, perfil[2326].first, 0.00001)
        assertEquals(-27.15023887506, perfil[2326].second, 0.00001)
    }

    @Test
    fun `Generar perfil prueba 2`() {
        val paso = 0.01
        val valoresTheta = ArrayList<Double>()

        var calculadora: CalculadoraSVAJ = CalculadoraDetenimientoBajo()
        for (i in 0..360 step paso) {
            when {
                i <= 60 -> valoresTheta.add(calculadora.calcularDesplazamiento(i.aRadianes(),
                    0.toDouble(), 60.aRadianes(), 0.toDouble()))
                i <= 150 -> {
                    calculadora = CalculadoraSubidaMAS()
                    valoresTheta.add(calculadora.calcularDesplazamiento((i-60).aRadianes(),
                        13.toDouble(), 90.aRadianes(), 0.toDouble()))
                }
                i <= 230 -> {
                    calculadora = CalculadoraSubida345()
                    valoresTheta.add(calculadora.calcularDesplazamiento((i-150).aRadianes(),
                        7.toDouble(), 80.aRadianes(), 13.toDouble()))
                }
                i <= 290 -> {
                    calculadora = CalculadoraDetenimientoAlto()
                    valoresTheta.add(calculadora.calcularDesplazamiento((i-230).aRadianes(),
                        0.toDouble(), 60.aRadianes(), 20.toDouble()))
                }
                i <= 360 -> {
                    calculadora = CalculadoraBajada4567()
                    valoresTheta.add(calculadora.calcularDesplazamiento((i-290).aRadianes(),
                        20.toDouble(), 70.aRadianes(), 20.toDouble()))
                }
            }
        }

        val perfil =
            GeneradorPerfil().calcularPerfil(valoresTheta, paso, 20.toDouble())

        assertEquals(20.0, perfil[0].first, 0.00001)
        assertEquals(0.0, perfil[0].second, 0.00001)

        assertEquals(10.0, perfil[6000].first, 0.00001)
        assertEquals(17.3205080756888, perfil[6000].second, 0.00001)

        assertEquals(-28.5788383248865, perfil[15000].first, 0.00001)
        assertEquals(16.5, perfil[15000].second, 0.00001)

        assertEquals(-25.7115043874616, perfil[23000].first, 0.00001)
        assertEquals(-30.6417777247591, perfil[23000].second, 0.00001)

        assertEquals(13.6808057330268, perfil[29000].first, 0.00001)
        assertEquals(-37.5877048314363, perfil[29000].second, 0.00001)

        assertEquals(20.0, perfil[36000].first, 0.00001)
        assertEquals(0.0, perfil[36000].second, 0.00001)
    }
}