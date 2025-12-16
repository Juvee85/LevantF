package com.rafd.levanf

import android.content.Context
import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.FirebaseApp
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import svaj.Tramo

@RunWith(RobolectricTestRunner::class)
class PruebasValidacionDatos {

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        FirebaseApp.initializeApp(context)
    }

    @Test
    fun `Validar datos correctos`() {
        val tramos = listOf(
            Tramo("Subida", 180, "4-5-6-7", 25.0),
            Tramo("Bajada", 180, "Cicloidal", 25.0)
        )

        val scenario = ActivityScenario.launch(RadialLinealActivity::class.java)

        scenario.onActivity { activity ->
            activity.findViewById<EditText>(R.id.etVelocidad).setText("1200")

            val resultado =activity.datosValidos(tramos)
            assert(resultado)
        }
    }

    @Test
    fun `No validar si eje x es mayor a 360`() {
        val tramos = listOf(
            Tramo("Subida", 180, "4-5-6-7", 25.0),
            Tramo("Bajada", 280, "Cicloidal", 25.0)
        )

        val scenario = ActivityScenario.launch(RadialLinealActivity::class.java)

        scenario.onActivity { activity ->
            activity.findViewById<EditText>(R.id.etVelocidad).setText("1200")

            val resultado =activity.datosValidos(tramos)
            assert(!resultado)
        }
    }

    @Test
    fun `No validar si eje x es menor a 360`() {
        val tramos = listOf(
            Tramo("Subida", 80, "4-5-6-7", 25.0),
            Tramo("Bajada", 180, "Cicloidal", 25.0)
        )

        val scenario = ActivityScenario.launch(RadialLinealActivity::class.java)

        scenario.onActivity { activity ->
            activity.findViewById<EditText>(R.id.etVelocidad).setText("1200")

            val resultado =activity.datosValidos(tramos)
            assert(!resultado)
        }
    }

    @Test
    fun `No validar si no se escribe velocidad`() {
        val tramos = listOf(
            Tramo("Subida", 180, "4-5-6-7", 25.0),
            Tramo("Bajada", 180, "Cicloidal", 25.0)
        )

        val scenario = ActivityScenario.launch(RadialLinealActivity::class.java)

        scenario.onActivity { activity ->
            val resultado =activity.datosValidos(tramos)
            assert(!resultado)
        }
    }

    @Test
    fun `No validar si la altura final no es 0`() {
        val tramos = listOf(
            Tramo("Subida", 180, "4-5-6-7", 25.0),
            Tramo("Bajada", 180, "Cicloidal", 12.0)
        )

        val scenario = ActivityScenario.launch(RadialLinealActivity::class.java)

        scenario.onActivity { activity ->
            activity.findViewById<EditText>(R.id.etVelocidad).setText("1200")

            val resultado = activity.datosValidos(tramos)
            assert(!resultado)
        }
    }

    @Test
    fun `No permitir solo segmentos de subida`() {
        val tramos = listOf(
            Tramo("Subida", 180, "4-5-6-7", 25.0),
            Tramo("Subida", 180, "Cicloidal", 25.0)
        )

        val scenario = ActivityScenario.launch(RadialLinealActivity::class.java)

        scenario.onActivity { activity ->
            activity.findViewById<EditText>(R.id.etVelocidad).setText("1200")

            val resultado =activity.datosValidos(tramos)
            assert(!resultado)
        }
    }

    @Test
    fun `No permitir solo segmentos de bajada`() {
        val tramos = listOf(
            Tramo("Bajada", 180, "4-5-6-7", 25.0),
            Tramo("Bajada", 180, "Cicloidal", 25.0)
        )

        val scenario = ActivityScenario.launch(RadialLinealActivity::class.java)

        scenario.onActivity { activity ->
            activity.findViewById<EditText>(R.id.etVelocidad).setText("1200")

            val resultado =activity.datosValidos(tramos)
            assert(!resultado)
        }
    }

    @Test
    fun `Validar varios segmentos de subida consecutivos`() {
        val tramos = listOf(
            Tramo("Subida", 90, "4-5-6-7", 20.0),
            Tramo("Subida", 90, "4-5-6-7", 5.0),
            Tramo("Bajada", 180, "Cicloidal", 25.0)
        )

        val scenario = ActivityScenario.launch(RadialLinealActivity::class.java)

        scenario.onActivity { activity ->
            activity.findViewById<EditText>(R.id.etVelocidad).setText("1200")

            val resultado =activity.datosValidos(tramos)
            assert(resultado)
        }
    }

    @Test
    fun `Validar varios segmentos de bajada consecutivos`() {
        val tramos = listOf(
            Tramo("Subida", 180, "4-5-6-7", 25.0),
            Tramo("Bajada", 100, "MAS", 12.0),
            Tramo("Bajada", 80, "Cicloidal", 13.0)
        )

        val scenario = ActivityScenario.launch(RadialLinealActivity::class.java)

        scenario.onActivity { activity ->
            activity.findViewById<EditText>(R.id.etVelocidad).setText("1200")

            val resultado =activity.datosValidos(tramos)
            assert(resultado)
        }
    }
}