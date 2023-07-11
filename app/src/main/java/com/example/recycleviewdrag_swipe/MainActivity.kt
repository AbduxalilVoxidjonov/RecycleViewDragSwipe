package com.example.recycleviewdrag_swipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleviewdrag_swipe.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Collections

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val array = mutableListOf<String>()

        val myCustomAdapter = MyCustomAdapter(array)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.apply {
            adapter = myCustomAdapter

        }
        myCustomAdapter.setListener(object: RecycleViewListener{
            override fun onItemClicked(position: Int) {

            }

            override fun onDeleteClicked(position: Int) {
                array.removeAt(position)
                myCustomAdapter.notifyItemRemoved(position)
            }

        })

        val touchpadSwipe= ItemTouchHelper(object: MyTouchItemHelper() {
            override fun onItemSwipeToDelete(position: Int) {
                val removedTitle = array.removeAt(position)
                myCustomAdapter.notifyItemRemoved(position)

                Snackbar.make(
                    this@MainActivity,
                    binding.recyclerView,
                    "$removedTitle is going to be removed",
                    Snackbar.LENGTH_SHORT
                )
                    .setAction("Tiklash") {
                        array.add(position, removedTitle)
                        myCustomAdapter.notifyItemInserted(position)
                    }.show()
            }

            override fun onItemMove(fromPosition: Int, toPosition: Int) {
                Collections.swap(array, fromPosition, toPosition)
                myCustomAdapter.notifyItemMoved(fromPosition, toPosition)
            }
        })

        touchpadSwipe.attachToRecyclerView(binding.recyclerView)

        binding.addBtn.setOnClickListener {
            array.add(binding.titleEt.text.toString())
            binding.titleEt.text.clear()
        }

    }
}