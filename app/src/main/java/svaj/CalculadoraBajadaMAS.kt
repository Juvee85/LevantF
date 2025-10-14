package svaj

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

class CalculadoraBajadaMAS : CalculadoraSVAJ {

    override fun calcularDesplazamiento(x: Double, altura: Double, beta: Double, alturaInicial: Double): Double {
        return altura - ((altura/2) * (1 - (cos(PI * x / beta)))) + (alturaInicial-altura)
    }

    override fun calcularVelocidad(x: Double, altura: Double, beta: Double, w: Double): Double {
        return -(PI/beta) * (altura/2) * sin(PI * (x/beta)) * w
    }

    override fun calcularAceleracion(x: Double, altura: Double, beta: Double, w: Double): Double {
        return -(PI.pow(2)/beta.pow(2)) * (altura/2) * cos(PI * (x/beta)) * w.pow(2)
    }

    override fun calcularSacudimiento(x: Double, altura: Double, beta: Double, w: Double): Double {
        return ((PI.pow(3)/beta.pow(3))  * (altura/2) * sin(PI * (x/beta))) * w.pow(3)
    }
}