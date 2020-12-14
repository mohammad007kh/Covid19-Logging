package com.musicoverflow.restapitest.services

import retrofit2.Call
import retrofit2.http.GET
import com.musicoverflow.restapitest.models.Country

interface CountryService {

    @GET("countries")
    fun getAffectedCountryList () : Call<List<Country>>

}