package com.musicoverflow.restapitest.helpers

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.musicoverflow.restapitest.R
import com.musicoverflow.restapitest.activities.CountryStatus
import com.musicoverflow.restapitest.models.Country
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException

    class CountriesAdapter(private val countriesList: List<Country>) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return countriesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${countriesList.size} ")

        return holder.bind(countriesList[position])

    }

    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {

        var imageView = itemView.findViewById<ImageView>(R.id.ivFlag)
        var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvCases = itemView.findViewById<TextView>(R.id.tvCases)


        fun bind(country: Country) {
            tvTitle.text = country.country
            tvCases.text = "Cases :${country.cases}"
            Picasso.get().load(country.countryInfo.flag).into(imageView)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, CountryStatus::class.java)
                intent.putExtra("countryName", country.country)
                intent.putExtra("countryContinent",country.continent)
                intent.putExtra("countryFlag", country.countryInfo.flag)
                intent.putExtra("countryPopulation", country.population.toString())
                intent.putExtra( "casesToday", country.todayCases.toString())
                intent.putExtra( "deathsToday", country.todayDeaths.toString())
                intent.putExtra("recoveredToday", country.todayRecovered.toString())
                intent.putExtra("totalDeath", country.deaths.toString())
                intent.putExtra("totalCases", country.cases.toString())
                intent.putExtra("totalRecovered", country.recovered.toString())


                itemView.context.startActivity(intent)

            }
        }

    }
}