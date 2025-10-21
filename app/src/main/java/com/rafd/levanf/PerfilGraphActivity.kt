package com.rafd.levanf

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.ScatterData
import com.github.mikephil.charting.data.ScatterDataSet
import com.google.android.material.textfield.TextInputLayout
import com.rafd.levanf.databinding.ActivityPerfilGraphBinding
import exportar.Exportador
import utils.step
import exportar.ExportadorExcel
import exportar.Perfil
import kotlin.math.cos
import kotlin.math.sin


class PerfilGraphActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilGraphBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPerfilGraphBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = binding.toolbar
        val textInputLayout = TextInputLayout(this)
        textInputLayout.setPadding(80,30, 80, 30)
        val etNombre = EditText(this)
        textInputLayout.addView(etNombre)

        val extras = intent.extras
        if (extras != null) {
            toolbar.setNavigationOnClickListener {
                val intent = Intent(this, GraphsActivity::class.java)
                intent.putExtra("tramos", extras.get("tramos") as ArrayList<Radial_LinealActivity.Tramo>)
                intent.putExtra("rpm", extras.getDouble("rpm"))
                intent.putExtra("paso", extras.getDouble("paso"))
                startActivity(intent)
            }

            val valoresTeta = extras.get("valoresTeta") as ArrayList<Double>
            val paso = extras.getDouble("paso")
            val radioBase = extras.getDouble("diametroBase") / 2
            val radioRodillo = extras.getDouble("diametroRodillo") / 2

            val entries = ArrayList<Entry>()
            val pares = ArrayList<Pair<Double, Double>>()
            for (i in 0..360 step paso) {
                val teta = valoresTeta.get((i*(1/paso)).toInt())
                val radioBaseX = radioBase * cos(i.aRadianes())
                val radioBaseY = radioBase * sin(i.aRadianes())
                val tetaX = teta * cos(i.aRadianes())
                val tetaY = teta * sin(i.aRadianes())

                val x = radioBaseX + tetaX
                val y = radioBaseY + tetaY

                entries.add(Entry(x.toFloat(), y.toFloat()))
                pares.add(Pair(x, y))
            }

            setupLineChart(binding.graficaPerfil, "Perfil")
            loadChartData(binding.graficaPerfil, entries, "Perfil")

            val folderPicker =
                this.registerForActivityResult(ActivityResultContracts.OpenDocumentTree()) { uri ->
                    uri?.let { selectedFolderUri ->
                        contentResolver.takePersistableUriPermission(
                            selectedFolderUri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                        )

                        val nombre = etNombre.getText().toString()
                        val perfil = Perfil(nombre, "Seguidor", "Lineal",
                            radioRodillo,
                            radioBase,
                            pares)
                        val exportador = ExportadorExcel()
                        exportador.exportar(perfil, this, selectedFolderUri)
                    }
                }

            binding.btnExportar.setOnClickListener {
                AlertDialog.Builder(this)
                    .setTitle("Nombre del archivo")
                    .setView(textInputLayout)
                    .setPositiveButton("Aceptar") { dialog, whichButton ->
                        val nombre = etNombre.getText().toString()
                        val perfil = Perfil(nombre, "Seguidor", "Lineal",
                            radioRodillo,
                            radioBase,
                            pares)
                        val exportador = ExportadorExcel()
                        folderPicker.launch(null)
                    }
                    .setNegativeButton("Cancelar") { dialog, whichButton -> }
                    .show()
            }

        }

    }

    fun Double.aRadianes(): Double {
        return this * Math.PI / 180
    }

    private fun setupLineChart(scatterChart: ScatterChart, descText: String) {
        // Habilitar interacciones
        scatterChart.setTouchEnabled(true)
        scatterChart.setPinchZoom(true)

        // Configuración de los ejes
        val xAxis = scatterChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(true)

        val yAxisLeft = scatterChart.axisLeft
        yAxisLeft.setDrawGridLines(true)

        val yAxisRight = scatterChart.axisRight
        yAxisRight.isEnabled = false

        // Configuración de la leyenda
        val legend = scatterChart.legend
        legend.form = Legend.LegendForm.LINE

        scatterChart.post {
            val description = Description()
            description.text = descText
            description.textSize = 14f
            description.textColor = Color.DKGRAY
            description.isEnabled = true

            // Medir el ancho del texto
            val paint = Paint()
            paint.textSize = description.textSize
            val textWidth = paint.measureText(description.text)

            // Ajustar la posición correctamente
            val centerX = scatterChart.width / 2.0f + textWidth / 2f
            val positionY = 40f // Altura de la descripción

            description.setPosition(centerX, positionY)

            // Margen superior extra para evitar que se superponga
            scatterChart.extraTopOffset = 40f

            scatterChart.description = description
            scatterChart.invalidate() // Redibujar
        }
    }

    private fun loadChartData(scatterChart: ScatterChart, entries: ArrayList<Entry>, nombreGrafica: String) {
        val dataSet = ScatterDataSet(entries, nombreGrafica).apply {
            color = Color.BLUE
            valueTextColor = Color.BLACK
            setDrawValues(false)
        }

        val scatterData = ScatterData(dataSet)
        scatterChart.data = scatterData
        scatterChart.invalidate()
    }
}