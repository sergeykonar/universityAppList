package com.example.universityapp.adapter

import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.UniversityItem
import com.example.universityapp.R

class UniversityItemAdapter(var data: ArrayList<UniversityItem> = ArrayList()): RecyclerView.Adapter<UniversityItemAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val text: TextView = view.findViewById(R.id.name)
        val website: TextView = view.findViewById(R.id.websiteUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_university_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.text.text = data[position].name
            holder.website.text = data[position].web_pages[0]
            Linkify.addLinks(holder.website, Linkify.WEB_URLS)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setValue(arrayList: ArrayList<UniversityItem>) {
        data = arrayList
        notifyDataSetChanged()
    }
}