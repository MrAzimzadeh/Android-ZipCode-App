package com.azimzada.zipcode.ViewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azimzada.zipcode.Constants
import com.azimzada.zipcode.api.Api
import com.azimzada.zipcode.model.ResultDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class State {
    SUCCESS, ERROR
}

class MainActivityViewModel : ViewModel() {
    lateinit var api: Api
    var zipCodeLiveData = MutableLiveData<ResultDTO>()
    var state = MutableLiveData<State>()
    fun getZipCode(context: Context, zipcode: String) {
        api = Constants.getApi()
        api.getZipCodeData(zipcode).enqueue(object : Callback<ResultDTO> {
            override fun onResponse(call: Call<ResultDTO>, response: Response<ResultDTO>) {
                val data: ResultDTO? = response.body()
                this@MainActivityViewModel.zipCodeLiveData.postValue(data)
                if (data?.country != null && data?.state != null && data?.city != null) {
                    this@MainActivityViewModel.state.postValue(State.SUCCESS)
                } else {
                    this@MainActivityViewModel.state.postValue(State.ERROR)
                }
            }

            override fun onFailure(call: Call<ResultDTO>, t: Throwable) {
                Toast.makeText(context, "An error has occurred", Toast.LENGTH_LONG).show()
            }


        })

    }

}