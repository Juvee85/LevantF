package com.rafd.levanf

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PruebasIngresarDatos {

    @get:Rule
    val activityRule = ActivityScenarioRule(RadialLinealActivity::class.java)

    @Test
    fun ingresarDatosValidos() {
        onView(withId(R.id.agregarTramos)).perform(click())
        onView(withId(R.id.agregarTramos)).perform(click())

        // Ingresa los datos en el primer segmento
        onData(anything())
            .inAdapterView(withId(R.id.lista))
            .atPosition(0)
            .onChildView(withId(R.id.segmentos))
            .perform(click())

        onView(ViewMatchers.withText("Subida"))
            .inRoot(RootMatchers.isPlatformPopup())
            .perform(click())

        onData(anything())
            .inAdapterView(withId(R.id.lista))
            .atPosition(0)
            .onChildView(withId(R.id.eje))
            .perform(ViewActions.replaceText("180"), ViewActions.closeSoftKeyboard())

        onData(anything())
            .inAdapterView(withId(R.id.lista))
            .atPosition(0)
            .onChildView(withId(R.id.altura))
            .perform(ViewActions.replaceText("25"), ViewActions.closeSoftKeyboard())


        // Ingresa los datos en el segundo segmento
        onData(anything())
            .inAdapterView(withId(R.id.lista))
            .atPosition(1)
            .onChildView(withId(R.id.eje))
            .perform(ViewActions.replaceText("180"), ViewActions.closeSoftKeyboard())

        onData(anything())
            .inAdapterView(withId(R.id.lista))
            .atPosition(1)
            .onChildView(withId(R.id.altura))
            .perform(ViewActions.replaceText("25"), ViewActions.closeSoftKeyboard())

        onData(anything())
            .inAdapterView(withId(R.id.lista))
            .atPosition(1)
            .onChildView(withId(R.id.segmentos))
            .perform(click())

        onView(ViewMatchers.withText("Bajada"))
            .inRoot(RootMatchers.isPlatformPopup())
            .perform(click())

        onView(withId(R.id.etVelocidad)).perform(ViewActions.typeText("1200"),
            ViewActions.closeSoftKeyboard())

        onView(withId(R.id.generar)).check(ViewAssertions.matches(isEnabled()))
    }

    /**
     * Función auxiliar para encontrar las vistas en una posicion determinada
     */
    fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: View): Boolean {
                val parent = view.parent as? ViewGroup ?: return false
                if (parent.childCount <= position) return false
                val child = parent.getChildAt(position)
                return itemMatcher.matches(child)
            }
        }
    }
}