package svaj

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

/**
 * Calculculadora para graficar el desplazamiento, velocidad, aceleración, y el sacudimiento de una
 * leva en un segmento de subida con las funciones MAS
 */
class CalculadoraSubidaMAS : CalculadoraSVAJ {

    /**
     * Calcula el desplazamiento en la subida de una leva con la función MAS.
     *
     * La ecuación usada es:
     * $$\frac{h}{2} \left[ 1 - cos\left(\pi \frac{\theta}{\beta} \right)\right] + h_{i}$$
     * @usesMathJax
     */
    override fun calcularDesplazamiento(x: Double, altura: Double, beta: Double, alturaInicial: Double): Double {
        return ((altura/2) * (1 - cos(PI * (x/beta)))) + alturaInicial
    }

    /**
     * Calcula la velocidad en la subida de una leva con la función MAS
     *
     * La ecuación usada es:
     * $$\left[\frac{\pi}{\beta} \frac{h}{2}sen\left(\pi \frac{\theta}{\beta}\right)\right] \omega$$
     * @usesMathJax
     */
    override fun calcularVelocidad(x: Double, altura: Double, beta: Double, w: Double): Double {
        return (PI/beta) * (altura/2) * sin(PI * (x/beta)) * w
    }

    /**
     * Calcula la aceleración en la subida de una leva con la función MAS
     *
     * La ecuación usada es:
     * $$\left[\frac{\pi^{2}}{\beta^{2}} \frac{h}{2}cos\left(\pi \frac{\theta}{\beta}\right)\right] \omega^{2}$$
     * @usesMathJax
     */
    override fun calcularAceleracion(x: Double, altura: Double, beta: Double, w: Double): Double {
        return (PI.pow(2)/beta.pow(2)) * (altura/2) * cos(PI * (x/beta)) * w.pow(2)
    }

    /**
     * Calcula el sacudimiento en la subida de una leva con la función MAS
     *
     * La ecuación usada es:
     * $$\left[-\frac{\pi^{3}}{\beta^{3}} \frac{h}{2}sen\left(\pi \frac{\theta}{\beta}\right)\right] \omega^{3}$$
     * @usesMathJax
     */
    override fun calcularSacudimiento(x: Double, altura: Double, beta: Double, w: Double): Double {
        return -(PI.pow(3)/beta.pow(3)) * (altura/2) * sin(PI * (x/beta)) * w.pow(3)
    }
}