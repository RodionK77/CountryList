package com.example.countrylist.Retrofit

import com.example.countrylist.CountryItem
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("https://restcountries.com/v2/all?fields=name,flag,alpha2Code,capital,region")
    fun getCountryList(): Call<List<CountryItem>>
}