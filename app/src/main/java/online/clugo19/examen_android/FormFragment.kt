package online.clugo19.examen_android

// FormFragment.kt

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import online.clugo19.examen_android.databinding.FragmentFormBinding


class FormFragment : Fragment() {

    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Inicializar Spinner con el array de escuelas
        val escuelas = resources.getStringArray(R.array.escuelas_array)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, escuelas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerEscuelas.adapter = adapter

        // Configurar TimePickers a 24 horas
        binding.timePickerEntrada.setIs24HourView(true)
        binding.timePickerSalida.setIs24HourView(true)

        binding.btnGuardar.setOnClickListener {
            val escuelaSeleccionada = binding.spinnerEscuelas.selectedItem.toString()
            val day = binding.datePicker.dayOfMonth
            val month = binding.datePicker.month + 1 // Recordar que los meses empiezan en 0
            val year = binding.datePicker.year
            val fecha = "$day/$month/$year"

            val horaEntrada = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                "${binding.timePickerEntrada.hour}:${binding.timePickerEntrada.minute}"
            } else {
                "${binding.timePickerEntrada.currentHour}:${binding.timePickerEntrada.currentMinute}"
            }
            val horaSalida = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                "${binding.timePickerSalida.hour}:${binding.timePickerSalida.minute}"
            } else {
                "${binding.timePickerSalida.currentHour}:${binding.timePickerSalida.currentMinute}"
            }
            val detalle = binding.editTextDetalle.text.toString()

            // Crear registro y agregarlo al repositorio
            val registro = Registro(escuelaSeleccionada, fecha, horaEntrada, horaSalida, detalle)
            RegistroRepository.registros.add(registro)
            Toast.makeText(requireContext(), "Registro guardado", Toast.LENGTH_SHORT).show()

            // Opcional: Limpiar el campo de detalle o reiniciar campos según se desee
            binding.editTextDetalle.text.clear()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

