package com.simx.dailytest.photos.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.simx.dailytest.R
import com.simx.dailytest.data.models.Photos
import com.simx.dailytest.databinding.DialogDetailPhotoBinding

@SuppressLint("ValidFragment")
class DialogPhotoDetail  constructor(var photos: Photos):DialogFragment() {
    private lateinit var binding:DialogDetailPhotoBinding
    private lateinit var vm:DialogPhotoViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_detail_photo, container,false)
        vm = DialogPhotoViewModel()
        binding.dialogVm = vm
        vm.image.set(photos.url)
        binding.setLifecycleOwner(this)
        binding.dialogVm = vm
        return binding.root
    }
}