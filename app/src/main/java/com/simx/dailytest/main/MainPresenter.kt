package com.simx.dailytest.main

import com.simx.dailytest.data.models.Albums

interface MainPresenter {
    fun initData(it: List<Albums>?)
    fun showEmpty()
    fun showError(message: String?)
}