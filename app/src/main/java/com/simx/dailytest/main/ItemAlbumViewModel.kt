package com.simx.dailytest.main

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.simx.dailytest.R
import com.simx.dailytest.data.models.Albums

class ItemAlbumViewModel (albums: Albums):BaseObservable(){
    val title = ObservableField<String>(albums.title)
    var image =ObservableField<Int>(R.drawable.placeholder)
}