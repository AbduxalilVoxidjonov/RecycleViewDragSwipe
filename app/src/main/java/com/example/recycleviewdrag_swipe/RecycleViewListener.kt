package com.example.recycleviewdrag_swipe

interface RecycleViewListener {
    fun onItemClicked(position: Int)
    fun onDeleteClicked(position: Int)
}