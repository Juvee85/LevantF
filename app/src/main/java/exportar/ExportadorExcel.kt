package exportar

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class ExportadorExcel : Exportador {

    override fun exportar(perfil: Perfil, context: Context, uri: Uri) {
        val workbook = XSSFWorkbook()
        val workSheet = workbook.createSheet()

        val primeraCelda = workSheet
            .createRow(0)
            .createCell(0)
        primeraCelda.setCellValue(perfil.nombre)

        val segundaCelda = workSheet
            .createRow(1)
            .createCell(0)
        segundaCelda.setCellValue(perfil.tipo)

        val terceraCelda = workSheet
            .createRow(2)
            .createCell(0)
        terceraCelda.setCellValue(perfil.movimiento)

        val filaDiametroBase = workSheet.createRow(3)
        val cabeceraDiametroBase = filaDiametroBase
            .createCell(0)
        cabeceraDiametroBase.setCellValue("Diametro Base:")
        val celdaDiametroBase = filaDiametroBase
            .createCell(1)
        celdaDiametroBase.setCellValue(perfil.radioBase * 2)

        val filaDiametroSeguidor = workSheet.createRow(4)
        val cabeceraDiametroSeguidor = filaDiametroSeguidor
            .createCell(0)
        cabeceraDiametroSeguidor.setCellValue("Diametro Seguidor:")
        val celdaDiametroSeguidor = filaDiametroSeguidor
            .createCell(1)
        celdaDiametroSeguidor.setCellValue(perfil.radioSeguidor * 2)

        val filaCabecera = workSheet.createRow(5)
        val celdaX = filaCabecera
            .createCell(0)
        celdaX.setCellValue("X")

        val celdaY = filaCabecera
            .createCell(1)
        celdaY.setCellValue("Y")

        for (i in 0..perfil.paresXY.size - 1) {
            val filaValores = workSheet.createRow(i + 6)
            val celdaValorX = filaValores
                .createCell(0)
            celdaValorX.setCellValue(perfil.paresXY[i].first)

            val celdaValorY = filaValores
                .createCell(1)
            celdaValorY.setCellValue(perfil.paresXY[i].second)
        }

        val resolver = context.contentResolver
        val folderDocumentUri = DocumentsContract.buildDocumentUriUsingTree(
            uri,
            DocumentsContract.getTreeDocumentId(uri)
        )

        val docUri = DocumentsContract.createDocument(
            resolver,
            folderDocumentUri,
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            "${perfil.nombre}.xlsx"
        )
        docUri?.let {
            resolver.openOutputStream(it)?.use { outputStream ->
                workbook.write(outputStream)
            }
        }

        workbook.close()
    }
}