package com.inzynierkaApp.app.sample.ui.cosmetics.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inzynierkaApp.app.domain.entities.film.playlistinfo.newcosmetics.ListOfNewItemsEntity
import com.inzynierkaApp.app.sample.databinding.ItemFilmBinding
import com.inzynierkaApp.app.sample.ui.extensions.loadFromUrl
import java.util.*

class AllCosmeticsAdapter(val listener: AllCosmeticsAdapterItemListener) :
    RecyclerView.Adapter<AllCosmeticsAdapter.ContentViewHolder>() {

    private var cosmeticsList: MutableList<ListOfNewItemsEntity> = mutableListOf()

    private var searchList: MutableList<ListOfNewItemsEntity> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(modes: List<ListOfNewItemsEntity>) {
        cosmeticsList = modes.toMutableList()
        countItems(cosmeticsList)
        searchList.addAll(cosmeticsList)
        notifyDataSetChanged()
    }

    private fun countItems(list: MutableList<ListOfNewItemsEntity>) {
        var temporaryCount = 0
        list.forEach {
            temporaryCount += 1
            it.count = temporaryCount
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onTextChange(newText: String?): Boolean {
        searchList.clear()
        val searchText = newText?.lowercase(Locale.getDefault())
        if (searchText != null) {
            if (searchText.isNotEmpty())
                searchList.clear()
                cosmeticsList.forEach {
                if (it.name?.lowercase(Locale.getDefault())?.contains(searchText) == true) {
                    searchList.add(it)
                }
            }
            notifyDataSetChanged()
        }
        else {
            searchList.clear()
            searchList.addAll(cosmeticsList)
            notifyDataSetChanged()
        }
        return false
    }

    inner class ContentViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cosmetic: ListOfNewItemsEntity) {
            binding.apply {
                name.text = cosmetic.name
                with(type) {
                    visibility = View.VISIBLE
                    type.text = cosmetic.type.rarity
                }
                numberOfPlaylist.text = cosmetic.count.toString()
                cosmetic.gameplayTags?.let { getTag(it.size, it, binding) }
                image.loadFromUrl(cosmetic.images.icon ?: cosmetic.images.smallIcon)
                clickableConstraintLayout.setOnClickListener { listener.onItemClicked(cosmetic.id) }
            }
        }
    }

    private fun getTag(size: Int, list: List<String>, binding: ItemFilmBinding) {
        var temporaryLength = 0

        for (i in 0 until size) {
            if (i < 3) {
                temporaryLength += list[i].removeRange(0, list[i].lastIndexOf(".") + 1).length
            } else
                break
            binding.apply {
                when (i) {
                    0 -> {
                        firstTagsContainer.visibility = View.VISIBLE
                        list[i].apply {
                            firstTag.text = this.removeRange(0, this.lastIndexOf(".") + 1)
                        }
                    }
                    1 -> {
                        if (temporaryLength < 19) {
                            secondTagContainer.visibility = View.VISIBLE
                            list[i].apply {
                                secondTag.text = this.removeRange(0, this.lastIndexOf(".") + 1)
                            }
                        }
                    }
                    2 -> {
                        if (temporaryLength < 19  ) {
                            thirdTagContainer.visibility = View.VISIBLE
                            list[i].apply {
                                thirdTag.text = this.removeRange(0, this.lastIndexOf(".") + 1)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContentViewHolder(
            ItemFilmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    fun clear() {
        searchList.clear()
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(searchList[position])
    }

    override fun getItemCount(): Int {
        return searchList.size
    }
}

interface AllCosmeticsAdapterItemListener {
    fun onItemClicked(cosmeticId: String)
}


