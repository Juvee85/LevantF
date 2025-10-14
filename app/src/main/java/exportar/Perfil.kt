package exportar

data class Perfil(val nombre: String,
    val tipo: String,
    val movimiento: String,
    val radioSeguidor: Double,
    val radioBase: Double,
    val paresXY: List<Pair<Double, Double>>)
