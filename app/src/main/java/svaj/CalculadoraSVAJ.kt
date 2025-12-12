package svaj

/**
 * Define los métodos para que una calculadora realice las operaciones para graficar el
 * desplazamiento, velocidad, aceleración, y el sacudimiento de una leva
 */
interface CalculadoraSVAJ {

    /**
     * Calcula el desplazamiento de una leva
     */
    fun calcularDesplazamiento(x: Double, altura: Double, beta: Double, alturaInicial: Double): Double

    /**
     * Calcula la velocidad de una leva
     */
    fun calcularVelocidad(x: Double, altura: Double, beta: Double, w: Double): Double

    /**
     * Calcula la acelearción de una leva
     */
    fun calcularAceleracion(x: Double, altura: Double, beta: Double, w: Double): Double

    /**
     * Calcula el sacudimiento de una leva
     */
    fun calcularSacudimiento(x: Double, altura: Double, beta: Double, w: Double): Double
}