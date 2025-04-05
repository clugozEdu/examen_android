package online.clugo19.examen_android

// MainActivity.kt
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import online.clugo19.examen_android.databinding.ActivityMainBinding
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Toast
import java.util.Calendar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listaEscuelas = arrayOf("Escuela A", "Escuela B", "Escuela C")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaEscuelas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerEscuelas.adapter = adapter

        // Configurar DatePicker
        binding.etFecha.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, dayOfMonth ->
                binding.etFecha.setText("$dayOfMonth/${month + 1}/$year")
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        // Configurar TimePicker para hora de entrada
        binding.etHoraEntrada.setOnClickListener {
            val calendar = Calendar.getInstance()
            TimePickerDialog(this, { _, hourOfDay, minute ->
                binding.etHoraEntrada.setText(String.format("%02d:%02d", hourOfDay, minute))
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }

        // Configurar TimePicker para hora de salida
        binding.etHoraSalida.setOnClickListener {
            val calendar = Calendar.getInstance()
            TimePickerDialog(this, { _, hourOfDay, minute ->
                binding.etHoraSalida.setText(String.format("%02d:%02d", hourOfDay, minute))
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }

        // Botón Guardar
        binding.btnGuardar.setOnClickListener {
            val escuela = binding.spinnerEscuelas.selectedItem.toString()
            val fecha = binding.etFecha.text.toString()
            val horaEntrada = binding.etHoraEntrada.text.toString()
            val horaSalida = binding.etHoraSalida.text.toString()
            val detalle = binding.etDetalle.text.toString()

            if (fecha.isEmpty() || horaEntrada.isEmpty() || horaSalida.isEmpty() || detalle.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Guardar datos en SharedPreferences
            val sharedPref = getSharedPreferences("visitas", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            val visita = "$escuela|$fecha|$horaEntrada|$horaSalida|$detalle"
            val visitasGuardadas = sharedPref.getString("listaVisitas", "") ?: ""
            val nuevaLista = if (visitasGuardadas.isEmpty()) visita else "$visitasGuardadas;\n$visita"
            editor.putString("listaVisitas", nuevaLista)
            editor.apply()

            // Actualizar el TextView con las visitas guardadas
            // Actualizar el TextView con las visitas guardadas
            binding.tvVisitas.text = "Visitas Guardadas:\n$nuevaLista"

            // Opcional: Limpiar los campos del formulario después de guardar
            binding.etFecha.text.clear()
            binding.etHoraEntrada.text.clear()
            binding.etHoraSalida.text.clear()
            binding.etDetalle.text.clear()
        }
    }
}



