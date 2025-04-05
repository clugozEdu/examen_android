package online.clugo19.examen_android

// RegistrosAdapter.kt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RegistrosAdapter(private val registros: List<Registro>) : RecyclerView.Adapter<RegistrosAdapter.RegistroViewHolder>() {

    inner class RegistroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRegistro: TextView = itemView.findViewById(R.id.tvRegistro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_registro, parent, false)
        return RegistroViewHolder(view)
    }

    override fun onBindViewHolder(holder: RegistroViewHolder, position: Int) {
        val registro = registros[position]
        holder.tvRegistro.text = "Escuela: ${registro.escuela}\nFecha: ${registro.fecha}\nHora Entrada: ${registro.horaEntrada}\nHora Salida: ${registro.horaSalida}\nDetalle: ${registro.detalle}"
    }

    override fun getItemCount(): Int = registros.size
}

