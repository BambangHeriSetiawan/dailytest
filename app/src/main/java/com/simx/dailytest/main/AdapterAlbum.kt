package com.simx.dailytest.main

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simx.dailytest.R
import com.simx.dailytest.data.models.Albums
import com.simx.dailytest.databinding.ItemAlbumBinding

class AdapterAlbum(var album:List<Albums>, var listener:AdapterAlbum.OnAdapterClickListener) : RecyclerView.Adapter<AdapterAlbum.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album,parent,false)
        return Holder(ItemAlbumBinding.bind(view))
    }

    override fun getItemCount(): Int {
        return  album.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(album[position])
        holder.itemView.setOnClickListener { listener.onAlbumCliked(album[position]) }
    }

    fun update(album: List<Albums>){
        this.album = album
        notifyDataSetChanged()
    }

    interface OnAdapterClickListener{
        fun onAlbumCliked(albums: Albums)
    }
    class Holder(var binding:ItemAlbumBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(albums: Albums){
            with(binding){
                albumVm = ItemAlbumViewModel(albums)
                executePendingBindings()
            }
        }

    }
}