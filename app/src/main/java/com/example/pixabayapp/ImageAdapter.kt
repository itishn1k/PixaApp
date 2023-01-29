package com.example.pixabayapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.pixabayapp.databinding.ItemPixaBinding

class ImageAdapter(private val list: List<ImageModel>) :
    RecyclerView.Adapter<ImageAdapter.ImageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder =
        ImageHolder(
            ItemPixaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ImageHolder, position: Int) =
        holder.onBind(list[position])


    class ImageHolder(private var binding: ItemPixaBinding) : ViewHolder(binding.root) {
        fun onBind(imageModel: ImageModel) {
            binding.ivImage.load(imageModel.largeImageURL)
        }
    }


}