package exportar

import android.content.Context
import android.net.Uri
import android.provider.DocumentsContract
import java.io.OutputStreamWriter

/**
 * Clase que permite exportar la información de un perfil geometrico a un archivo de texto
 */
class ExportadorTXT : Exportador {

    /**
     * Método que exporta un perfil a un archivo de texto
     *
     * @param perfil Perfil geometrico con la información a exportar
     * @param context Contexto de la aplicación desde el cual se exporta
     * @param uri Ubicación en el almacenamiento al cuál se exporta el archivo
     */
    override fun exportar(perfil: Perfil, context: Context, uri: Uri) {
        val resolver = context.contentResolver
        val folderDocumentUri = DocumentsContract.buildDocumentUriUsingTree(
            uri,
            DocumentsContract.getTreeDocumentId(uri)
        )

        val docUri = DocumentsContract.createDocument(
            resolver,
            folderDocumentUri,
            "text/plain",
            "${perfil.nombre}.txt"
        )

        docUri?.let {
            resolver.openOutputStream(it)?.use { outputStream ->
                OutputStreamWriter(outputStream).use { writer ->
                    writer.write(generarTXT(perfil))
                }
            }
        }
    }

    private fun generarTXT(perfil: Perfil): String {
        val sb = StringBuilder()
        for ((x, y) in perfil.paresXY) {
            sb.appendLine("$x $y 0.0")
        }

        return sb.toString()
    }
}