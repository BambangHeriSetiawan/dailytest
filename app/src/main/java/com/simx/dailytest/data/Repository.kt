package com.simx.dailytest.data

import com.simx.dailytest.data.models.Albums
import com.simx.dailytest.data.models.Photos
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object Repository {
    fun getAlbum(): Observable<List<Albums>>? {
        return API.Factory.create().getAlbums().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }
    fun getPhotos(albumId:Int): Observable<List<Photos>>? {
        return API.Factory.create().getPhotos(albumId).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }
}