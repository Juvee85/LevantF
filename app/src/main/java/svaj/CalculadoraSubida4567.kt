package svaj

import kotlin.math.pow

/**
 * Calculculadora para graficar el desplazamiento, velocidad, aceleración, y el sacudimiento de una
 * leva en un segmento de subida con las funciones polinomio 4-5-6-7
 */
class CalculadoraSubida4567 : CalculadoraSVAJ {

    /**
     * Calcula el desplazamiento en la subida de una leva con la función 4-5-6-7.
     *
     * La ecuación usada es:
     * $$h \left[35\left(\theta^{4} \over \beta^{4} \right) - 84\left(\theta^{5} \over \beta^{5} \right) + 70\left(\theta^{6} \over \beta^{6} \right) - 20\left(\theta^{7} \over \beta^{7} \right)\right] + h_{i}$$
     * @usesMathJax
     */
    override fun calcularDesplazamiento(
        x: Double,
        altura: Double,
        beta: Double,
        alturaInicial: Double
    ): Double {
        return altura * (35*(x/beta).pow(4) - 84*(x/beta).pow(5) + 70*(x/beta).pow(6) - 20*(x/beta).pow(7)) + alturaInicial
    }

    /**
     * Calcula la velocidad en la subida de una leva con la función 4-5-6-7.
     *
     * La ecuación usada es:
     * $$h \left[140\left(\theta^{3} \over \beta^{4} \right) - 420\left(\theta^{4} \over \beta^{5} \right) + 420\left(\theta^{5} \over \beta^{6} \right) - 140\left(\theta^{6} \over \beta^{7} \right)\right]\omega$$
     * @usesMathJax
     */
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

    /**
     * Calcula la aceleración en la subida de una leva con la función 4-5-6-7.
     *
     * La ecuación usada es:
     * $$h \left[420\left(\theta^{2} \over \beta^{4} \right) - 1680\left(\theta^{3} \over \beta^{5} \right) + 2100\left(\theta^{4} \over \beta^{6} \right) - 840\left(\theta^{5} \over \beta^{7} \right)\right]\omega^{2}$$
     * @usesMathJax
     */
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

    /**
     * Calcula el sacudimiento en la subida de una leva con la función 4-5-6-7.
     *
     * La ecuación usada es:
     * $$h \left[840\left(\theta \over \beta^{4} \right) - 5040\left(\theta^{2} \over \beta^{5} \right) + 8400\left(\theta^{3} \over \beta^{6} \right) - 4200\left(\theta^{4} \over \beta^{7} \right)\right]\omega^{3}$$
     * @usesMathJax
     */
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