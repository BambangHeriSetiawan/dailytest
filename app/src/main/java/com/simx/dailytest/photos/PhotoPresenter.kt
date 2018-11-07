package com.simx.dailytest.photos

import com.simx.dailytest.data.models.Photos

interface PhotoPresenter {
    fun showError(message: String?)
    fun setEmpty()
    fun initDataPhoto(it: List<Photos>?)
}