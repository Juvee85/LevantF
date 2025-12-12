package svaj

import kotlin.math.pow

/**
 * Calculculadora para graficar el desplazamiento, velocidad, aceleración, y el sacudimiento de una
 * leva en un segmento de subida con las funciones polinomio 3-4-5
 */
class CalculadoraSubida345 : CalculadoraSVAJ {

    /**
     * Calcula el desplazamiento en la subida de una leva con la función 3-4-5.
     *
     * La ecuación usada es:
     * $$h \left[10\left(\theta^{3} \over \beta^{3} \right) - 15\left(\theta^{4} \over \beta^{4} \right) + 6\left(\theta^{5} \over \beta^{5} \right)\right] + h_{i}$$
     * @usesMathJax
     */
    override fun calcularDesplazamiento(
        x: Double,
        altura: Double,
        beta: Double,
        alturaInicial: Double
    ): Double {
        return altura * (10*(x/beta).pow(3) - 15*(x/beta).pow(4) + 6*(x/beta).pow(5)) + alturaInicial
    }

    /**
     * Calcula la velocidad en la subida de una leva con la función 3-4-5.
     *
     * La ecuación usada es:
     * $$\left(h \left[30\left(\theta^{2} \over \beta^{3} \right) - 60\left(\theta^{3} \over \beta^{4} \right) + 30\left(\theta^{4} \over \beta^{5} \right)\right]\right)\omega$$
     * @usesMathJax
     */
    override fun calcularVelocidad(
        x: Double,
        altura: Double,
        beta: Double,
        w: Double
    ): Double {
        return (altura *
                    (30*(x.pow(2)/beta.pow(3)) -
                    60*(x.pow(3)/beta.pow(4)) +
                    30*(x.pow(4)/beta.pow(5)))) * w
    }

    /**
     * Calcula la aceleración en la subida de una leva con la función 3-4-5.
     *
     * La ecuación usada es:
     * $$\left(h \left[60\left(\theta \over \beta^{3} \right) - 180\left(\theta^{2} \over \beta^{4} \right) + 120\left(\theta^{3} \over \beta^{5} \right)\right]\right)\omega^{2}$$
     * @usesMathJax
     */
    override fun calcularAceleracion(
        x: Double,
        altura: Double,
        beta: Double,
        w: Double
    ): Double {
        return (altura *
                    (60*(x/beta.pow(3)) -
                    180*(x.pow(2)/beta.pow(4)) +
                    120*(x.pow(3)/beta.pow(5)))) * w.pow(2)
    }

    /**
     * Calcula el sacudimiento en la subida de una leva con la función 3-4-5.
     *
     * La ecuación usada es:
     * $$\left(h \left[\left(60 \over \beta^{3} \right) - 360\left(\theta \over \beta^{4} \right) + 360\left(\theta^{2} \over \beta^{5} \right)\right]\right)\omega^{3}$$
     * @usesMathJax
     */
    override fun calcularSacudimiento(
        x: Double,
        altura: Double,
        beta: Double,
        w: Double
    ): Double {
        return (altura *
                    ((60/beta.pow(3)) -
                    360*(x/beta.pow(4)) +
                    360*(x.pow(2)/beta.pow(5)))) * w.pow(3)
    }
}