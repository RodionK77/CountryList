package com.example.countrylist

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import java.util.*
import kotlin.collections.ArrayList

class CountryListAdapter(val activity: Activity) : RecyclerView.Adapter<CountryListAdapter.CountryHolder>() {

    var items = listOf<CountryItem>()
    var filterItems = listOf<CountryItem>()


    fun setCountryList(countryList: List<CountryItem>) {
        this.items = countryList
        this.filterItems = countryList
    }

    fun filter(charText: String) {
        var charText = charText
        charText = charText.lowercase(Locale.getDefault())
        if (charText.length == 0) { //jika string kosong
            filterItems = items
        } else { //filter dan buat arraybaru dengan filter query model
            val filteredList = mutableListOf<CountryItem>()
            for (wp in items) {
                if (wp.name!!.toLowerCase(Locale.getDefault()).contains(charText)) {
                    filteredList.add(wp)
                }
            }
            filterItems = filteredList
        }
        //memperbarui list adapter
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryHolder(view)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.bind(filterItems?.get(position)!!, activity)
    }

    override fun getItemCount() : Int{
        if(filterItems == null)return 0
        else return filterItems?.size!!
    }


    class CountryHolder(view: View) : RecyclerView.ViewHolder(view) {

        val flagImage = view.findViewById<ImageView>(R.id.flagImage)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvCapital = view.findViewById<TextView>(R.id.tvCapital)
        val tvRegion = view.findViewById<TextView>(R.id.tvRegion)


        fun bind(data: CountryItem, activity: Activity) {
            tvName.text = data.name +"(" + data.alpha2Code+")"
            tvCapital.text = "Столица: "+data.capital
            tvRegion.text = "Регион: "+data.region

            GlideToVectorYou.justLoadImage(activity, Uri.parse(data.flag), flagImage)
        }

    }
}