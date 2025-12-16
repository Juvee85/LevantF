package exportar

import android.content.Context
import android.net.Uri
import android.provider.DocumentsContract
import java.io.OutputStreamWriter

/**
 * Clase que permite exportar la información de un perfil geometrico a un archivo de extensión .dxf
 */
class ExportadorDXF : Exportador {

    /**
     * Método que exporta un perfil a un archivo de extensión .dxf
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
            "image/vnd.dxf",
            "${perfil.nombre}.dxf"
        )

        docUri?.let {
            resolver.openOutputStream(it)?.use { outputStream ->
                OutputStreamWriter(outputStream).use { writer ->
                    writer.write(generarDXF(perfil))
                }
            }
        }
    }

    fun generarDXF(perfil: Perfil): String {
        val sb = StringBuilder()

        // DXF HEADER
        sb.appendLine("0")
        sb.appendLine("SECTION")
        sb.appendLine("2")
        sb.appendLine("ENTITIES")

        // Entidad POLYLINE
        sb.appendLine("0")
        sb.appendLine("POLYLINE")
        sb.appendLine("8")
        sb.appendLine(perfil.nombre) // Nombre de capa
        sb.appendLine("66")
        sb.appendLine("1")
        sb.appendLine("10")
        sb.appendLine("0.0")
        sb.appendLine("20")
        sb.appendLine("0.0")
        sb.appendLine("30")
        sb.appendLine("0.0")

        // Añade puntos (x, y)
        perfil.paresXY.forEach { (x, y) ->
            sb.appendLine("0")
            sb.appendLine("VERTEX")
            sb.appendLine("10")
            sb.appendLine(x.toString())
            sb.appendLine("20")
            sb.appendLine(y.toString())
            sb.appendLine("30")
            sb.appendLine("0.0")
        }

        // Fin de polilinea
        sb.appendLine("0")
        sb.appendLine("SEQEND")

        // Fin de sección de entidades
        sb.appendLine("0")
        sb.appendLine("ENDSEC")
        sb.appendLine("0")
        sb.appendLine("EOF")

        return sb.toString()
    }
}