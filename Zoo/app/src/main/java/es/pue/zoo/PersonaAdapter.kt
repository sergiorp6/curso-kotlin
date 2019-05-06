package es.pue.zoo

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import es.pue.zoo.model.Persona
import kotlinx.android.synthetic.main.item_nombre.view.*

class PersonaAdapter(private val alumnos: List<Persona>) : RecyclerView.Adapter<PersonaAdapter.PersonaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_nombre, parent, false)

        return PersonaHolder(itemView)
    }

    override fun getItemCount(): Int = alumnos.size

    override fun onBindViewHolder(viewHolder: PersonaHolder, position: Int) {
        viewHolder.renderPersona(alumnos[position])
    }

    class PersonaHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                val intent = Intent(it.context, MainActivity::class.java)
                it.context.startActivity(intent)
            }
        }

        fun renderPersona(persona: Persona) {
            Picasso.get().load(persona.avatar).into(view.textView_avatar)
            view.textView_nombre.text = persona.nombre
            view.textView_apellidos.text = persona.apellido
        }
    }
}