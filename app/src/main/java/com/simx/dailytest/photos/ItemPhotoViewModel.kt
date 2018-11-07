package com.simx.dailytest.photos

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.simx.dailytest.data.models.Photos

class ItemPhotoViewModel (photos: Photos):BaseObservable(){
    val title = ObservableField<String>(photos.title)
    val image = ObservableField<String>(photos.thumbnailUrl)
}