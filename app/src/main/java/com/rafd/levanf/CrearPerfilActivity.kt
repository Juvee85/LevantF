package com.rafd.levanf

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.rafd.levanf.databinding.ActivityCrearPerfilBinding
import svaj.Tramo

class CrearPerfilActivity : AppCompatActivity() {
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
                onBackPressedDispatcher.onBackPressed()
            }

            binding.btnVerPerfil.setOnClickListener {
                val intent = Intent(this, PerfilGraphActivity::class.java)
                intent.putExtra("tramos", extras.get("tramos") as ArrayList<Tramo>)
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