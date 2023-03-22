package com.inzynierkaApp.app.sample.ui.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.news.BrItemsEntity
import com.inzynierkaApp.app.sample.databinding.ItemNewsBinding
import com.inzynierkaApp.app.sample.ui.extensions.loadFromUrl

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ContentViewHolder>() {

    private var cosmeticsList: MutableList<BrItemsEntity> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(modes: List<BrItemsEntity>) {
        cosmeticsList = modes.toMutableList()
        notifyDataSetChanged()
    }

    inner class ContentViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: BrItemsEntity) {
            binding.apply {
                title.text = news.tabTitle
                description.text = news.body
                image.loadFromUrl(news.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContentViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    fun clear() {
        cosmeticsList.clear()
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(cosmeticsList[position])
    }

    override fun getItemCount(): Int {
        return cosmeticsList.size
    }
}