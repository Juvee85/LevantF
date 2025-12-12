package svaj

/**
 * Calculadora para graficar el detenimiento alto de una leva
 */
class CalculadoraDetenimientoAlto: CalculadoraSVAJ {

    /**
     * Regresa el desplazamiento de una leva en detenimiento, este siempre es igual a la altura inicial
     */
    override fun calcularDesplazamiento(x: Double, altura: Double, beta: Double, alturaInicial: Double): Double {
        return alturaInicial
    }

    /**
     * Regresa la velocidad de una leva en detenimiento, esta siempre es igual a 0
     */
    override fun calcularVelocidad(x: Double, altura: Double, beta: Double, w: Double): Double {
        return 0.0
    }

    /**
     * Regresa la aceleración de una leva en detenimiento, esta siempre es igual a 0
     */
    override fun calcularAceleracion(x: Double, altura: Double, beta: Double, w: Double): Double {
        return 0.0
    }

    /**
     * Regresa el sacudimiento de una leva en detenimiento, este siempre es igual a 0
     */
    override fun calcularSacudimiento(x: Double, altura: Double, beta: Double, w: Double): Double {
        return 0.0
    }
}