package svaj

/**
 * Define los métodos para que un generador de calculadoras de un tipo de segmento particular
 * provea calculadoras para las distintas ecuaciones con las que se puede calcular
 *
 * Implementa el patrón de fábrica abstracta
 */
interface GeneradorCalculadora {
    /**
     * Regresa una calculadora para la función cicloidal
     */
    fun crearCalculadoraCicloidal(): CalculadoraSVAJ

    /**
     * Regresa una calculadora para la función MAS
     */
    fun crearCalculadoraMAS(): CalculadoraSVAJ

    /**
     * Regresa una calculadora para la función 4-5-6-7
     */
    fun crearCalculadora4567(): CalculadoraSVAJ

    /**
     * Regresa una calculadora para la función 3-4-5
     */
    fun crearCalculadora345(): CalculadoraSVAJ
}