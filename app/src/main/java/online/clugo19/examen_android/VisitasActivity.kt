package online.clugo19.examen_android


import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import online.clugo19.examen_android.databinding.ActivityVisitasBinding

class VisitasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVisitasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisitasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Leer la lista de visitas almacenadas en SharedPreferences
        val sharedPref = getSharedPreferences("visitas", Context.MODE_PRIVATE)
        val listaVisitas = sharedPref.getString("listaVisitas", "No hay visitas registradas") ?: "No hay visitas registradas"

        // Mostrar los datos en el TextView
        binding.tvVisitas.text = listaVisitas
    }
}

