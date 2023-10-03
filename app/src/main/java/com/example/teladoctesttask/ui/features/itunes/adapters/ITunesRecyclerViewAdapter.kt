package com.example.teladoctesttask.ui.features.itunes.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teladoctesttask.databinding.ItemItunesAlbumBinding
import com.example.teladoctesttask.presentation.features.itunes.models.AlbumData

class ITunesRecyclerViewAdapter(private var data: List<AlbumData>) :
    RecyclerView.Adapter<ITunesRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemItunesAlbumBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemItunesAlbumBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding.tvName.text = data[position].name
    }

    override fun getItemCount() = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<AlbumData>) {
        this.data = newData
        notifyDataSetChanged()
    }
}