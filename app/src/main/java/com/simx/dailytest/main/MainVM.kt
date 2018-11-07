package com.simx.dailytest.main

import androidx.databinding.BaseObservable
import com.simx.dailytest.data.Repository
import io.reactivex.disposables.CompositeDisposable

class MainVM(private var presenter: MainPresenter,
             private var compositeDisposable: CompositeDisposable):BaseObservable() {
    fun getAlbum(){
        compositeDisposable.add(
            Repository.getAlbum()!!.subscribe(
                {
                    if (it.isNotEmpty()) presenter.initData(it)
                        else presenter.showEmpty()
                },
                {
                    presenter.showError(it.message)
                }
            )
        )
    }
    fun clear(){
        if (!compositeDisposable.isDisposed)compositeDisposable.dispose()
    }
}