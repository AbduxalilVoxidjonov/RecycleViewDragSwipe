package com.example.recycleviewdrag_swipe

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class MyTouchItemHelper():ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN or ItemTouchHelper.UP,ItemTouchHelper.LEFT){
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {

        val fromPosition= viewHolder.adapterPosition
        val toPosition= target.adapterPosition
        onItemMove(fromPosition,toPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onItemSwipeToDelete(viewHolder.adapterPosition)
    }
    abstract  fun onItemSwipeToDelete(position: Int)

    abstract  fun onItemMove(fromPosition: Int, toPosition: Int)
}