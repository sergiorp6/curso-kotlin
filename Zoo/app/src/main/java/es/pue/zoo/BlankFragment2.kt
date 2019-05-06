package es.pue.zoo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.pue.zoo.model.Persona

class BlankFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false)
        val layout = LinearLayoutManager(activity)
        val items = view.findViewById<RecyclerView>(R.id.recyclerView_items)

        items.layoutManager = layout
        items.adapter = PersonaAdapter(initAlumnos())

        return view
    }

    companion object {
        fun newInstance() = BlankFragment2()
    }

    private fun initAlumnos(): List<Persona> {
        val sergio = Persona("Sergio", "Rodriguez", "https://image.flaticon.com/icons/png/128/145/145862.png", 5f)
        val monica = Persona("Mónica", "Orteu", "https://image.flaticon.com/icons/png/128/145/145862.png", 7f)
        val mario = sergio.copy(nombre = "Mario")

        return listOf(sergio, monica, mario)
    }
}
