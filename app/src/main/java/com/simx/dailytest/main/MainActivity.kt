package com.simx.dailytest.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.simx.dailytest.R
import com.simx.dailytest.data.models.Albums
import com.simx.dailytest.databinding.MainActivityBinding
import com.simx.dailytest.photos.PhotosActivity
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity(),MainPresenter, AdapterAlbum.OnAdapterClickListener {
    override fun onAlbumCliked(albums: Albums) {
        var phosoIntent = Intent(this@MainActivity,PhotosActivity::class.java)
        phosoIntent.putExtra("id",albums.id)
        startActivity(phosoIntent)
    }

    private lateinit var binding:MainActivityBinding
    private lateinit var vm:MainVM
    lateinit var adapterAlbum: AdapterAlbum

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.main_activity)
        vm = MainVM(this, CompositeDisposable())
        binding.setLifecycleOwner(this)
        binding.mainVm = vm
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "ALBUM"
        adapterAlbum = AdapterAlbum(listOf(),this)
        initRcv()
        vm.getAlbum()

    }

    private fun initRcv() {
        binding.rcvAlbum.setHasFixedSize(true)
        binding.rcvAlbum.itemAnimator = DefaultItemAnimator()
        binding.rcvAlbum.layoutManager = GridLayoutManager(this,2) as GridLayoutManager
        binding.rcvAlbum.adapter = adapterAlbum

    }

    override fun initData(albums: List<Albums>?) {
        adapterAlbum.update(albums!!)
    }

    override fun showEmpty() {
        binding.tvEmpty.visibility  = View.VISIBLE
    }

    override fun showError(message: String?) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        vm.clear()
        super.onDestroy()

    }
}
