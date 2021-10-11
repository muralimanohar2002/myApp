package com.example.myapp.Models

data class RecyclerList(val articles: ArrayList<RecyclerData>)
data class RecyclerData(val content: String, val title: String, val urlToImage: String, val publishedAt: String, val source: Sources, val url: String)
data class Sources(val name: String)