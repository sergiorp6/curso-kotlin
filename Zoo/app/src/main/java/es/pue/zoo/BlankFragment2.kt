package es.pue.zoo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BlankFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false)
        val nombres = context?.resources?.getStringArray(R.array.nombres)?.toList()
        val layout = LinearLayoutManager(activity)
        val items = view.findViewById<RecyclerView>(R.id.recyclerView_items)

        items.layoutManager = layout
        items.adapter = ItemsAdapter(nombres!!)

        return view
    }

    companion object {
        fun newInstance() = BlankFragment2()
    }
}
