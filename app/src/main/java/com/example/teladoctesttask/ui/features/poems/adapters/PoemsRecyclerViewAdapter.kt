package com.example.teladoctesttask.ui.features.poems.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teladoctesttask.databinding.ItemWordOccurrenceBinding
import com.example.teladoctesttask.presentation.features.poems.models.WordsOccurrencesData

class PoemsRecyclerViewAdapter(private var data: List<WordsOccurrencesData>) :
    RecyclerView.Adapter<PoemsRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemWordOccurrenceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWordOccurrenceBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding.tvWord.text = data[position].word
        viewHolder.binding.tvOccurrence.text = data[position].occurrence.toString()
    }

    override fun getItemCount() = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<WordsOccurrencesData>) {
        this.data = newData
        notifyDataSetChanged()
    }
}