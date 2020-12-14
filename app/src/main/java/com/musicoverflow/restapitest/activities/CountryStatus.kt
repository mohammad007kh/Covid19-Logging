package com.musicoverflow.restapitest.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.convertTo
import com.google.gson.GsonBuilder
import com.musicoverflow.restapitest.R
import com.musicoverflow.restapitest.models.Country
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_country_status.*
import okhttp3.*
import java.io.IOException

class CountryStatus : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_status)

        supportActionBar?.title = intent.getStringExtra("countryName")
        Picasso.get().load(intent.getStringExtra("countryFlag")).into(CountryImage)
        population.text = "population: "+intent.getStringExtra("countryPopulation")
        continent.text = "Continent: "+intent.getStringExtra("countryContinent")
        txtCasesToday.text = "Cases Today: "+intent.getStringExtra("casesToday")
        txtDeathsToday.text = "Deaths Today: "+intent.getStringExtra("deathsToday")
        txtRecoveredToday.text = "Recovered Today: "+intent.getStringExtra("recoveredToday")
        txtTotalCases.text ="Total Cases: "+intent.getStringExtra("totalCases")
        txtTotalDeath.text = "Total Deaths: "+intent.getStringExtra("totalDeath")
        txtTotalRecovered.text = "Total Recovered: "+intent.getStringExtra("totalRecovered")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true;
    }
}
