package com.example.webviewuimodeissue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommonAdapter : RecyclerView.Adapter<CommonAdapter.CommonViewHolder>() {

    class CommonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var items: List<Int> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout, parent, false)
        return CommonViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        (holder.itemView as TextView).text = position.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}