package com.example.recycleviewdrag_swipe

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewdrag_swipe.databinding.ItemListBinding

class MyCustomAdapter(val array: List<String>): RecyclerView.Adapter<MyCustomAdapter.MyViewHolder>() {
    private var recyclerView: RecycleViewListener? = null
    inner class MyViewHolder (val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(titletext: String) {
            binding.textView.text = titletext
        }
        init {
           binding.deleteBtn.setOnClickListener{
               recyclerView?.onDeleteClicked(adapterPosition)
           }
        }
    }
    fun setListener(listener: RecycleViewListener){
        recyclerView = listener
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val bindings = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(bindings)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(array[position])
    }
}
