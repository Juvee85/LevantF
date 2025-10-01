package com.rafd.levanf

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CrearPerfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_perfil)

        // Configura la toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val extras = intent.extras
        if (extras != null) {
            toolbar.setNavigationOnClickListener {
                val intent = Intent(this, GraphsActivity::class.java)
                intent.putExtra("tramos", extras.get("tramos") as ArrayList<Radial_LinealActivity.Tramo>)
                intent.putExtra("rpm", extras.getDouble("rpm"))
                startActivity(intent)
            }
        }
    }
}