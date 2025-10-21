package utils

import android.text.InputFilter
import android.widget.EditText
import java.math.BigDecimal

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

infix fun IntProgression.step(step: Double): Iterable<Double> {
    require(step > 0.0) { "Paso debe ser positvo" }
    val inicio = this.first.toDouble()
    val fin = this.last.toDouble()

    return generateSequence(inicio) { it + step }
        .takeWhile { it <= fin }
        .asIterable()
}
