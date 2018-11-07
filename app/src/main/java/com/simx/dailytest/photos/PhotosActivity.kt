package com.simx.dailytest.photos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.simx.dailytest.R
import com.simx.dailytest.data.models.Photos
import com.simx.dailytest.databinding.PhotosActivityBinding
import com.simx.dailytest.photos.dialog.DialogPhotoDetail
import io.reactivex.disposables.CompositeDisposable

class PhotosActivity : AppCompatActivity(),PhotoPresenter, AdapterPhotos.OnAdapterClickListener {
    override fun onPhotoliked(photos: Photos) {
        val dialog = DialogPhotoDetail(photos)
        dialog.show(supportFragmentManager,photos.title)
    }


    private lateinit var binding:PhotosActivityBinding
    private lateinit var vm:PhotoActivityVM
    private lateinit var adapterPhotos: AdapterPhotos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.photos_activity)
        vm = PhotoActivityVM(this, CompositeDisposable())
        binding.setLifecycleOwner(this)
        binding.photoVM = vm
        vm.isShowProgress.set(false)
        if (intent?.extras !=null){
            val albumId = intent?.extras?.getInt("id")
            vm.getListPhoto(albumId!!)
        }
        adapterPhotos = AdapterPhotos(listOf(),this)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "PHOTOS"
        initRcv()
    }

    private fun initRcv() {
        binding.rcvPhoto.setHasFixedSize(true)
        binding.rcvPhoto.layoutManager = GridLayoutManager(this@PhotosActivity,2)
        binding.rcvPhoto.itemAnimator = DefaultItemAnimator()
        binding.rcvPhoto.adapter = adapterPhotos
    }

    override fun showError(message: String?) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun setEmpty() {
        binding.tvEmpty.visibility = View.VISIBLE
    }

    override fun initDataPhoto(it: List<Photos>?) {
        adapterPhotos.update(it!!)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
