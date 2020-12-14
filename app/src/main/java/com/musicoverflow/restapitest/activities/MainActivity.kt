package com.musicoverflow.restapitest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.musicoverflow.restapitest.R
import com.musicoverflow.restapitest.helpers.CountriesAdapter
import com.musicoverflow.restapitest.models.Country
import com.musicoverflow.restapitest.services.CountryService
import com.musicoverflow.restapitest.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var countryrecycler: RecyclerView

    //private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //toolbar = findViewById(R.id.activity)

        countryrecycler = findViewById(R.id.country_recycler)
        loadCountries()
    }

    private fun loadCountries() {
        //initiate the service
        val destinationService  = ServiceBuilder.buildService(CountryService::class.java)
        val requestCall = destinationService.getAffectedCountryList()
        //make network call asynchronously

        requestCall.enqueue(object : Callback<List<Country>>{
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    val countryList  = response.body()!!

                    Log.d("Response", "countrylist size : ${countryList.size}")

                    countryrecycler.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@MainActivity,1)
                        adapter = CountriesAdapter(response.body()!!)
                    }
                }else{
                    Toast.makeText(this@MainActivity, "Something went wrong ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong $t", Toast.LENGTH_SHORT).show()
            }
        })
    }

}