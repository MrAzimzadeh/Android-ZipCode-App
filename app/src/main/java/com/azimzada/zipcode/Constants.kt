package com.azimzada.zipcode

import com.azimzada.zipcode.api.Api
import com.azimzada.zipcode.network.RetrofitClient

class Constants {
    companion object{
        val BASE_URL = "https://ziptasticapi.com/"

        fun getApi(): Api {
            return RetrofitClient.getClient(BASE_URL).create(Api::class.java)
        }
    }
}