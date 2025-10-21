package svaj

import kotlin.math.pow

class CalculadoraBajada345 : CalculadoraSVAJ {
    override fun calcularDesplazamiento(
        x: Double,
        altura: Double,
        beta: Double,
        alturaInicial: Double
    ): Double {
        return altura-(altura * (10*(x/beta).pow(3) - 15*(x/beta).pow(4) + 6*(x/beta).pow(5))) + (alturaInicial-altura)
    }

    override fun calcularVelocidad(
        x: Double,
        altura: Double,
        beta: Double,
        w: Double
    ): Double {
        return (-altura *
                    (30*(x.pow(2)/beta.pow(3)) -
                    60*(x.pow(3)/beta.pow(4)) +
                    30*(x.pow(4)/beta.pow(5)))) * w
    }

    override fun calcularAceleracion(
        x: Double,
        altura: Double,
        beta: Double,
        w: Double
    ): Double {
        return (-altura *
                    (60*(x/beta.pow(3)) -
                    180*(x.pow(2)/beta.pow(4)) +
                    120*(x.pow(3)/beta.pow(5)))) * w.pow(2)
    }

    override fun calcularSacudimiento(
        x: Double,
        altura: Double,
        beta: Double,
        w: Double
    ): Double {
        return (-altura *
                    ((60/beta.pow(3)) -
                    360*(x/beta.pow(4)) +
                    360*(x.pow(2)/beta.pow(5)))) * w.pow(3)
    }
}