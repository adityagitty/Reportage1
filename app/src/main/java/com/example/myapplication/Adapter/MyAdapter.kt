package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.ModelClasses.Article

import com.example.myapplication.R

class MyAdapter(val context:Context,val articles: List<Article>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val article = articles[position]
        holder.txtTitle.text = article.title
        holder.txtDescription.text = article.description
        Glide.with(context)
            .load(article.urlToImage)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.imageview);

    }

    override fun getItemCount(): Int {
        return articles.size
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
    val txtDescription = itemView.findViewById<TextView>(R.id.txtDiscription)
    val imageview = itemView.findViewById<ImageView>(R.id.image)
}