package com.sensyne.hospitals.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sensyne.hospitals.R
import com.sensyne.hospitals.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class HospitalsActivity : AppCompatActivity() {

    private val hospitalsViewModel: HospitalsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = hospitalsViewModel
        hospitalsViewModel.getHospitalInformation()
    }
}
