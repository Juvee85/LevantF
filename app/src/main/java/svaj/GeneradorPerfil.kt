package svaj

import utils.aRadianes
import kotlin.math.cos
import kotlin.math.sin

/**
 * Clase para hacer el cáculo del perfil geométrico de una leva
 */
class GeneradorPerfil {
    /**
     * Calcula los puntos de un perfíl de una leva a partir de los valores de theta, el paso entre
     * cada punto, y el radio de la base
     *
     * @param valoresTheta Lista con los valores de la posición de la leva
     * @param paso El paso utilizado para generar la lista de valores de posición
     * @param radioBase Radio de la base de la leva
     */
    fun calcularPerfil(valoresTheta: List<Double>, paso: Double, radioBase: Double): List<Pair<Double, Double>> {
        val pares = ArrayList<Pair<Double, Double>>(valoresTheta.size)

        for (i in valoresTheta.indices) {
            val angulo = (i * paso).aRadianes()

            val theta = valoresTheta[i]
            val radioBaseX = radioBase * cos(angulo)
            val radioBaseY = radioBase * sin(angulo)
            val thetaX = theta * cos(angulo)
            val thetaY = theta * sin(angulo)

            val x = radioBaseX + thetaX
            val y = radioBaseY + thetaY

            pares.add(x to y)
        }

        return pares
    }
}