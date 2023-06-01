package com.azimzada.zipcode.api

import com.azimzada.zipcode.model.ResultDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("{ZIPCODE}")
    fun getZipCodeData(@Path("ZIPCODE") ZIPCODE: String): Call<ResultDTO>
}