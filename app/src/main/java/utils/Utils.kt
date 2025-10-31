package utils

import android.text.InputFilter
import android.widget.EditText

/**
 * Método para limitar la cantidad de dígitos antes y después del punto decimal en un campo de
 * texto
 *
 * @param digitosMaximosAntesDecimal Cantidad de dígitos a los que se limita la parte entera
 * @param digitosMaximosDespuesDecimal Cantidad de dígitos a los que se limita la parte decimal
 */
fun EditText.limitarDecimal(digitosMaximosAntesDecimal: Int, digitosMaximosDespuesDecimal: Int) {
    val filter = InputFilter { source, start, end, dest, dstart, dend ->
        val newValue = dest.toString().substring(0, dstart) +
                source.subSequence(start, end) +
                dest.toString().substring(dend)

        val regex =
            Regex("^[0-9]{0,$digitosMaximosAntesDecimal}+((\\.[0-9]{0,$digitosMaximosDespuesDecimal})?)?$")

        if (newValue.isEmpty() || newValue.matches(regex)) {
            null
        } else {
            ""
        }
    }
    filters = arrayOf(filter)
}

/**
 * Método para establecer el paso que se utilizará para recorrer un rango entre 2 números enteros
 * a un número con parte decimal
 *
 * @param step Número con parte decimal mayor a 0 que se usa para recorrer un rango
 */
infix fun IntProgression.step(step: Double): Iterable<Double> {
    require(step > 0.0) { "Paso debe ser positvo" }
    val inicio = this.first.toDouble()
    val fin = this.last.toDouble()

    return generateSequence(inicio) { it + step }
        .takeWhile { it <= fin }
        .asIterable()
}

/**
 * Convierte un valor de rpm a radianes / segundo.
 */
fun Double.aRadianesSegundos(): Double {
    return this * 2 * Math.PI / 60
}

/**
 * Convierte un valor en grados a radianes.
 */
fun Int.aRadianes(): Double {
    return this * Math.PI / 180
}

/**
 * Convierte un valor con parte decimal en grados a radianes.
 */
fun Double.aRadianes(): Double {
    return this * Math.PI / 180
}

/**
 * Convierte un valor en radianes a grados.
 */
fun Double.aGrados(): Double {
    return this * 180 / Math.PI
}
