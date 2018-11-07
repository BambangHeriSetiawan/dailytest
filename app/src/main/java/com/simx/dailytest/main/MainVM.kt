package com.simx.dailytest.main

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.library.baseAdapters.BR
import com.simx.dailytest.data.Repository
import io.reactivex.disposables.CompositeDisposable

class MainVM(private var presenter: MainPresenter,
             private var compositeDisposable: CompositeDisposable):BaseObservable() {
    @Bindable
    var isShowProgress = ObservableField<Boolean>()
    set(value) {
        field = value
        notifyPropertyChanged(BR.isShowProgress)
    }

    fun getAlbum(){
        isShowProgress.set(true)
        compositeDisposable.add(
            Repository.getAlbum()!!.subscribe(
                {
                    isShowProgress.set(false)
                    if (it.isNotEmpty()) presenter.initData(it)
                        else presenter.showEmpty()
                },
                {
                    isShowProgress.set(false)
                    presenter.showError(it.message)
                }
            )
        )
    }
    fun clear(){
        if (!compositeDisposable.isDisposed)compositeDisposable.dispose()
    }
}