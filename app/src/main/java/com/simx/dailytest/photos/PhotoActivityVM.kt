package com.simx.dailytest.photos

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.library.baseAdapters.BR
import com.simx.dailytest.data.Repository
import io.reactivex.disposables.CompositeDisposable

class PhotoActivityVM(private var presenter:PhotoPresenter,
                      private var compositeDisposable: CompositeDisposable):BaseObservable() {
    @Bindable
    var isShowProgress = ObservableField<Boolean>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.isShowProgress)
        }

    fun getListPhoto(albumId: Int) {
        isShowProgress.set(true)
        compositeDisposable.add(
            Repository.getPhotos(albumId)!!.subscribe(
                {
                    isShowProgress.set(false)
                    if (!it.isEmpty()) presenter.initDataPhoto(it)
                        else presenter.setEmpty()
                },
                {
                    isShowProgress.set(false)
                    presenter.showError(it.message)
                }
            )
        )
    }
}