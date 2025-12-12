package com.rafd.levanf

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rafd.levanf.databinding.ActivityCrearPerfilBinding
import com.rafd.levanf.databinding.ActivityMenuPrincipalBinding

class CrearPerfil : AppCompatActivity() {
    private lateinit var binding: ActivityCrearPerfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCrearPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

            binding.btnVerPerfil.setOnClickListener {
                val intent = Intent(this, PerfilGraphActivity::class.java)
                intent.putExtra("tramos", extras.get("tramos") as ArrayList<Radial_LinealActivity.Tramo>)
                intent.putExtra("rpm", extras.getDouble("rpm"))
                intent.putExtra("valoresTeta", extras.get("valoresTeta") as ArrayList<Double>)
                intent.putExtra("paso", extras.getDouble("paso"))
                intent.putExtra("diametroBase", binding.etBase.text.toString().toDouble())
                intent.putExtra("diametroRodillo", binding.etRodillo.text.toString().toDouble())
                startActivity(intent)
            }
        }
    }
}