package svaj

import kotlin.math.pow

class CalculadoraSubida4567 : CalculadoraSVAJ {
    override fun calcularDesplazamiento(
        x: Double,
        altura: Double,
        beta: Double,
        alturaInicial: Double
    ): Double {
        return altura * (35*(x/beta).pow(4) - 84*(x/beta).pow(5) + 70*(x/beta).pow(6) - 20*(x/beta).pow(7)) + alturaInicial
    }

    override fun calcularVelocidad(
        x: Double,
        altura: Double,
        beta: Double,
        w: Double
    ): Double {
        return (altura *
                    (140*(x.pow(3)/beta.pow(4)) -
                    420*(x.pow(4)/beta.pow(5)) +
                    420*(x.pow(5)/beta.pow(6)) -
                    140*(x.pow(6)/beta.pow(7)))) * w
    }

    override fun calcularAceleracion(
        x: Double,
        altura: Double,
        beta: Double,
        w: Double
    ): Double {
        return (altura *
                    (420*(x.pow(2)/beta.pow(4)) -
                    1680*(x.pow(3)/beta.pow(5)) +
                    2100*(x.pow(4)/beta.pow(6)) -
                    840*(x.pow(5)/beta.pow(7)))) * w.pow(2)
    }

    override fun calcularSacudimiento(
        x: Double,
        altura: Double,
        beta: Double,
        w: Double
    ): Double {
        return (altura *
                    (840*(x/beta.pow(4)) -
                    5040*(x.pow(2)/beta.pow(5)) +
                    8400*(x.pow(3)/beta.pow(6)) -
                    4200*(x.pow(4)/beta.pow(7)))) * w.pow(3)
    }
}