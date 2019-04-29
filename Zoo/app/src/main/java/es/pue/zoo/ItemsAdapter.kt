package es.pue.zoo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ItemsAdapter(private val nombres: List<String>) : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_nombre, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = nombres.size

    override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
        val tvNombre = viewHolder.itemView.findViewById<TextView>(R.id.textView_nombre)

        tvNombre.text = nombres[position]
    }

    class ItemViewHolder(textView: View) : RecyclerView.ViewHolder(textView)
}