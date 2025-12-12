package exportar

import android.content.Context
import android.net.Uri

/**
 * Interface que define el método para exportar la información de un perfil geometrico a un archivo
 */
interface Exportador {

    /**
     * Método que exporta un perfil a un archivo
     *
     * @param perfil Perfil geometrico con la información a exportar
     * @param context Contexto de la aplicación desde el cual se exporta
     * @param uri Ubicación en el almacenamiento al cuál se exporta el archivo
     */
    fun exportar(perfil : Perfil, context: Context, uri: Uri)
}