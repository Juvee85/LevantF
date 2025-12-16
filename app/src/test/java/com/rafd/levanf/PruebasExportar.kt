package com.rafd.levanf

import exportar.ExportadorDXF
import exportar.ExportadorExcel
import exportar.ExportadorTXT
import exportar.Perfil
import org.junit.Test

class PruebasExportar {

    @Test
    fun `Generar perfil txt`() {
        val exportador = ExportadorTXT()
        val perfil = Perfil("Prueba", "", "",
            25.0, 90.0,
            listOf(Pair(1.0, 2.0), Pair(3.0, 4.0)))

        val txt = exportador.generarTXT(perfil)

        val lineas = txt.trim().split("\n")
        assert(lineas.size == 2)
        assert(lineas[0] == "1.0 2.0 0.0")
        assert(lineas[1] == "3.0 4.0 0.0")
    }

    @Test
    fun `Generar perfil DXF`() {
        val exportador = ExportadorDXF()
        val perfil = Perfil("Prueba", "", "",
            25.0, 90.0,
            listOf(Pair(1.0, 2.0), Pair(3.0, 4.0)))

        val dxf = exportador.generarDXF(perfil)

        val lineas = dxf.trim().split("\n")

        assert(lineas.size == 38)

        assert(lineas[0] == "0")
        assert(lineas[1] == "SECTION")
        assert(lineas[2] == "2")
        assert(lineas[3] == "ENTITIES")
        assert(lineas[4] == "0")
        assert(lineas[5] == "POLYLINE")
        assert(lineas[6] == "8")
        assert(lineas[7] == perfil.nombre)

        assert(lineas[8] == "66")
        assert(lineas[9] == "1")
        assert(lineas[10] == "10")
        assert(lineas[11] == "0.0")
        assert(lineas[12] == "20")
        assert(lineas[13] == "0.0")
        assert(lineas[14] == "30")
        assert(lineas[15] == "0.0")

        assert(lineas[16] == "0")
        assert(lineas[17] == "VERTEX")
        assert(lineas[18] == "10")
        assert(lineas[19] == "1.0")
        assert(lineas[20] == "20")
        assert(lineas[21] == "2.0")
        assert(lineas[22] == "30")
        assert(lineas[23] == "0.0")

        assert(lineas[24] == "0")
        assert(lineas[25] == "VERTEX")
        assert(lineas[26] == "10")
        assert(lineas[27] == "3.0")
        assert(lineas[28] == "20")
        assert(lineas[29] == "4.0")
        assert(lineas[30] == "30")
        assert(lineas[31] == "0.0")

        assert(lineas[32] == "0")
        assert(lineas[33] == "SEQEND")
        assert(lineas[34] == "0")
        assert(lineas[35] == "ENDSEC")
        assert(lineas[36] == "0")
        assert(lineas[37] == "EOF")
    }

    @Test
    fun `Generar perfi Excel`() {
        val exportador = ExportadorExcel()
        val perfil = Perfil("Prueba", "tipo", "movimiento",
            25.0, 90.0,
            listOf(Pair(1.0, 2.0), Pair(3.0, 4.0)))

        val workbook = exportador.generarWorkbook(perfil)
        val sheet = workbook.getSheetAt(0)

        assert(sheet.getRow(0).getCell(0).stringCellValue == perfil.nombre)

        assert(sheet.getRow(1).getCell(0).stringCellValue == perfil.tipo)

        assert(sheet.getRow(2).getCell(0).stringCellValue == perfil.movimiento)

        assert(sheet.getRow(3).getCell(0).stringCellValue == "Diametro Base:")
        assert(sheet.getRow(3).getCell(1).numericCellValue == 180.0)

        assert(sheet.getRow(4).getCell(0).stringCellValue == "Diametro Seguidor:")
        assert(sheet.getRow(4).getCell(1).numericCellValue == 50.0)

        assert(sheet.getRow(5).getCell(0).stringCellValue == "X")
        assert(sheet.getRow(5).getCell(1).stringCellValue == "Y")

        assert(sheet.getRow(6).getCell(0).numericCellValue == 1.0)
        assert(sheet.getRow(6).getCell(1).numericCellValue == 2.0)

        assert(sheet.getRow(7).getCell(0).numericCellValue == 3.0)
        assert(sheet.getRow(7).getCell(1).numericCellValue == 4.0)
    }
}