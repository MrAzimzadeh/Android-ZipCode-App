package com.azimzada.zipcode

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.azimzada.zipcode.ViewModels.MainActivityViewModel
import com.azimzada.zipcode.ViewModels.State
import com.azimzada.zipcode.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        binding.apply {
            countryTextView.visibility = View.GONE
            stateTextView.visibility = View.GONE
            cityTextView.visibility = View.GONE
        }
        binding.searchButton.setOnClickListener {
            viewModel.getZipCode(this, binding.zipCodeEditText.text.toString())
        }
        viewModel.state.observe(this) {
            when (it) {
                State.SUCCESS -> {
                    binding.countryTextView.apply {
                        visibility = View.VISIBLE
                        text = "country: ${viewModel.zipCodeLiveData.value?.country}"
                    }
                    binding.stateTextView.apply {
                        visibility = View.VISIBLE
                        text = "state: ${viewModel.zipCodeLiveData.value?.state}"
                    }
                    binding.cityTextView.apply {
                        visibility = View.VISIBLE
                        text = "city: ${viewModel.zipCodeLiveData.value?.city}"
                    }
                }
                State.ERROR -> {
                    binding.stateTextView.visibility = View.VISIBLE
                    binding.stateTextView.setText("Agzina Geleni Yazma!!")
                    binding.countryTextView.visibility = View.GONE
                    binding.cityTextView.visibility = View.GONE
                }
                else -> {}
            }
        }
    }
}