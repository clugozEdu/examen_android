package online.clugo19.examen_android

// DetailActivity.kt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import online.clugo19.examen_android.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    // Declaración de la variable de binding
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflar el layout utilizando view binding
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperar los datos enviados desde MainActivity
        val escuela = intent.getStringExtra("escuela")
        val fecha = intent.getStringExtra("fecha")
        val horaEntrada = intent.getStringExtra("horaEntrada")
        val horaSalida = intent.getStringExtra("horaSalida")
        val detalle = intent.getStringExtra("detalle")

        // Mostrar los datos en los TextViews
        binding.tvEscuela.text = "Escuela: $escuela"
        binding.tvFecha.text = "Fecha: $fecha"
        binding.tvHoraEntrada.text = "Hora de Entrada: $horaEntrada"
        binding.tvHoraSalida.text = "Hora de Salida: $horaSalida"
        binding.tvDetalle.text = "Detalle/Observación: $detalle"
    }
}

