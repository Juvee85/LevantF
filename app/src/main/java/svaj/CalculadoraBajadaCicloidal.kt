package svaj

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

/**
 * Calculculadora para graficar el desplazamiento, velocidad, aceleración, y el sacudimiento de una
 * leva en un segmento de bajada con las funciones cicloidales
 */
class CalculadoraBajadaCicloidal: CalculadoraSVAJ {

    /**
     * Calcula el desplazamiento en la bajada de una leva con la función cicloidal
     *
     * La ecuación usada es:
     * $$h - \left(h \left[\frac{\theta}{\beta} - \frac{1}{2\pi} sen\left(2\pi \frac{\theta}{\beta}\right)\right]\right) + \left(h_{i}-h\right)$$
     * @usesMathJax
     */
    override fun calcularDesplazamiento(x: Double, altura: Double, beta: Double, alturaInicial: Double): Double {
        return altura - (altura * ((x / beta) - ((1 / (2 * PI)) * sin(2 * PI * (x / beta))))) + (alturaInicial-altura)
    }

    /**
     * Calcula la velocidad en la subida de una leva con la función cicloidal.
     *
     * La ecuación usada es:
     * $$-\left(\frac{h}{\beta} \left[1 - cos\left(2\pi \frac{\theta}{\beta}\right)\right]\right) \omega$$
     * @usesMathJax
     */
    override fun calcularVelocidad(x: Double, altura: Double, beta: Double, w: Double): Double {
        return -((altura / beta) * (1 - cos(2 * PI * (x / beta)))) * w
    }

    /**
     * Calcula la aceleración en la subida de una leva con la función cicloidal.
     *
     * La ecuación usada es:
     * $$-\left[2\pi\left(\frac{h}{\beta^{2}}\right) sen\left(2\pi \frac{\theta}{\beta}\right)\right] \omega^{2}$$
     * @usesMathJax
     */
    override fun calcularAceleracion(x: Double, altura: Double, beta: Double, w: Double): Double {
        return -2 * PI * (altura / beta.pow(2)) * sin(2 * PI * (x / beta)) * w.pow(2)
    }

    /**
     * Calcula e scaudimiento en la subida de una leva con la función cicloidal.
     *
     * La ecuación usada es:
     * $$-\left[4\pi^{2} \frac{h}{\beta^{3}} cos\left(2\pi \frac{\theta}{\beta}\right)\right] \omega^{3}$$
     * @usesMathJax
     */
    override fun calcularSacudimiento(x: Double, altura: Double, beta: Double, w: Double): Double {
        return -4 * PI.pow(2) * (altura / beta.pow(3)) * cos(2 * PI * (x / beta)) * w.pow(3)
    }
}