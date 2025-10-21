package exportar

import android.content.Context
import android.net.Uri

interface Exportador {
    fun exportar(perfil : Perfil, context: Context, uri: Uri)
}