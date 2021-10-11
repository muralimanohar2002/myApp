package com.example.myapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.Models.RecyclerData
import com.example.myapp.R
import com.squareup.picasso.Picasso

class NewsRecyclerAdapter: RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>() {

    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position : Int, url: String)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    var newsList = ArrayList<RecyclerData>()

    fun setUpdatedData(newsList: ArrayList<RecyclerData>){
        this.newsList = newsList
        notifyDataSetChanged()
    }

    class NewsViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view){
        val newsPic = view.findViewById<ImageView>(R.id.news_image)
        val newsTitle = view.findViewById<TextView>(R.id.news_title)
        val newsDesc = view.findViewById<TextView>(R.id.news_desc)
        val publisher = view.findViewById<TextView>(R.id.publisher)
        val timing = view.findViewById<TextView>(R.id.time_publish)
        lateinit var url : String
        fun bind(data: RecyclerData){
            newsTitle.text = data.title
            newsDesc.text = data.content
            publisher.text = data.source.name
            timing.text = data.publishedAt

            url = data.url
            val urlImg = data.urlToImage
            Picasso.get().load(urlImg).into(newsPic)
        }

        init {
            view.setOnClickListener {
                listener.onItemClick(adapterPosition, url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_news, parent, false)
        return NewsViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }


}