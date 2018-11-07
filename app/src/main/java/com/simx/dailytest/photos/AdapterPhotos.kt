package com.simx.dailytest.photos

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simx.dailytest.R
import com.simx.dailytest.data.models.Photos
import com.simx.dailytest.databinding.ItemPhotoBinding

class AdapterPhotos(var photos:List<Photos>, var listener: OnAdapterClickListener) : RecyclerView.Adapter<AdapterPhotos.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo,parent,false)
        return Holder(ItemPhotoBinding.bind(view))
    }

    override fun getItemCount(): Int {
        return  photos.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(photos[position])
        holder.itemView.setOnClickListener { listener.onPhotoliked(photos[position]) }
    }

    fun update(photos: List<Photos>){
        this.photos = photos
        notifyDataSetChanged()
    }

    interface OnAdapterClickListener{
        fun onPhotoliked(photos: Photos)
    }
    class Holder(var binding:ItemPhotoBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(photos: Photos){
            with(binding){
                photoVm = ItemPhotoViewModel(photos)
                executePendingBindings()
            }
        }

    }
}