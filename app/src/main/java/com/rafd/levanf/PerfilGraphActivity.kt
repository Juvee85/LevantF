package com.rafd.levanf

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
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
import com.rafd.levanf.databinding.ActivityPerfilGraphBinding
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

        val perfilChart = binding.graficaPerfil
        val toolbar = binding.toolbar

        val extras = intent.extras
        if (extras != null) {
            toolbar.setNavigationOnClickListener {
                val intent = Intent(this, GraphsActivity::class.java)
                intent.putExtra("tramos", extras.get("tramos") as ArrayList<Radial_LinealActivity.Tramo>)
                intent.putExtra("rpm", extras.getDouble("rpm"))
                startActivity(intent)
            }

            val valoresTeta = extras.get("valoresTeta") as ArrayList<Array<Double>>
            val radioBase = extras.getDouble("diametroBase") / 2
            val radioRodillo = extras.getDouble("diametroRodillo") / 2

            val entries = ArrayList<Entry>()
            for (i in 0..360 step 10) {
                val teta = valoresTeta.get(i)
                val radioBaseX = radioBase * cos(i.aRadianes())
                val radioBaseY = radioBase * sin(i.aRadianes())
                val tetaX = teta.get(1) * cos(i.aRadianes())
                val tetaY = teta.get(1) * sin(i.aRadianes())

                val x = radioBaseX + tetaX
                val y = radioBaseY + tetaY

                entries.add(Entry(x.toFloat(), y.toFloat()))
            }

            setupLineChart(binding.graficaPerfil, "Perfil")
            loadChartData(binding.graficaPerfil, entries, "Perfil")
        }

    }

    fun Int.aRadianes(): Double {
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