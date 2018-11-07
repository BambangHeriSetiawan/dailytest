package com.simx.dailytest.photos

import androidx.databinding.BaseObservable
import com.simx.dailytest.data.Repository
import io.reactivex.disposables.CompositeDisposable

class PhotoActivityVM(private var presenter:PhotoPresenter,
                      private var compositeDisposable: CompositeDisposable):BaseObservable() {
    fun getListPhoto(albumId: Int) {
        compositeDisposable.add(
            Repository.getPhotos(albumId)!!.subscribe(
                {
                    if (!it.isEmpty()) presenter.initDataPhoto(it)
                        else presenter.setEmpty()
                },
                {
                    presenter.showError(it.message)
                }
            )
        )
    }
}