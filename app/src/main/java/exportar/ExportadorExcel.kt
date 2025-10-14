package exportar

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class ExportadorExcel : Exportador {

    override fun exportar(perfil: Perfil, context: Context) {
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

        val filaRadioBase = workSheet.createRow(3)
        val cabeceraRadioBase = filaRadioBase
            .createCell(0)
        cabeceraRadioBase.setCellValue("Radio Base:")
        val celdaRadioBase = filaRadioBase
            .createCell(1)
        celdaRadioBase.setCellValue(perfil.radioBase)

        val filaRadioSeguidor = workSheet.createRow(4)
        val cabeceraRadioSeguidor = filaRadioSeguidor
            .createCell(0)
        cabeceraRadioSeguidor.setCellValue("Radio Seguidor:")
        val celdaRadioSeguidor = filaRadioSeguidor
            .createCell(1)
        celdaRadioSeguidor.setCellValue(perfil.radioSeguidor)

        val filaCabecera = workSheet.createRow(5)
        val celdaX = filaCabecera
            .createCell(0)
        celdaX.setCellValue("X")

        val celdaY = filaCabecera
            .createCell(1)
        celdaY.setCellValue("Y")

        for (i in 0 .. perfil.paresXY.size-1) {
            val filaValores = workSheet.createRow(i+6)
            val celdaValorX = filaValores
                .createCell(0)
            celdaValorX.setCellValue(perfil.paresXY[i].first)

            val celdaValorY = filaValores
                .createCell(1)
            celdaValorY.setCellValue(perfil.paresXY[i].second)
        }

        val values = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "${perfil.nombre}.xlsx")
            put(MediaStore.MediaColumns.MIME_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS)
        }

        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Files.getContentUri("external"), values)

        uri?.let {
            resolver.openOutputStream(it)?.use { outputStream ->
                workbook.write(outputStream)
            }
        }

        workbook.close()
    }
}