package exportar

import android.content.Context

interface Exportador {
    fun exportar(perfil : Perfil, context: Context)
}