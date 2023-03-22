package com.inzynierkaApp.app.sample.ui.home

import android.annotation.SuppressLint
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.PlaylistInfoDetailsEntity
import com.inzynierkaApp.app.sample.databinding.ItemFilmBinding
import com.inzynierkaApp.app.sample.ui.extensions.loadFromUrl
import java.util.*

class ContentAdapter(val listener: CityAdapterItemListener) :
    RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {

    private var modeList: MutableList<PlaylistInfoDetailsEntity> = mutableListOf()

    private val searchList: ArrayList<PlaylistInfoDetailsEntity> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(modes: List<PlaylistInfoDetailsEntity>) {
        modeList = modes.toMutableList()
        modeList.removeAll { it.images.showcase == null && it.images.missionIcon == null }
        removeSlice(modeList,0, 35)
        countItems(modeList)
        searchList.addAll(modeList)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun sortData(position: Int) {
        when(position) {
            0 -> searchList.sortBy { it.name }
            1 -> searchList.sortByDescending { it.date }
            2 -> searchList.sortBy { it.count }
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onTextChange(newText: String?): Boolean {
        searchList.clear()
        val searchText = newText?.lowercase(Locale.getDefault())
        if (searchText != null) {
            if (searchText.isNotEmpty())
                searchList.clear()
                modeList.forEach {
                    if (it.name?.lowercase(Locale.getDefault())?.contains(searchText) == true) {
                        searchList.add(it)

                    }
                }
            notifyDataSetChanged()
        }
        else {
            searchList.clear()
            searchList.addAll(modeList)
            notifyDataSetChanged()
        }
        return false
    }


    private fun <T> removeSlice(list: MutableList<T>, from: Int, end: Int) {
        for (i in end downTo from) {
            list.removeAt(i)
        }
    }

    private fun countItems(list: MutableList<PlaylistInfoDetailsEntity>) {
        var temporaryCount = 0
        list.forEach {
            temporaryCount += 1
            it.count = temporaryCount
        }
    }

    inner class ContentViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mode: PlaylistInfoDetailsEntity) {
            binding.apply {
                name.text = mode.name
                numberOfPlaylist.text = mode.count.toString()
                with(date) {
                    visibility = View.VISIBLE
                    text = mode.date.substring(0,10).plus("  ").plus(
                        mode.date.substring(12, mode.date.length - 1)
                    )
                }
                image.loadFromUrl( mode.images.missionIcon ?: mode.images.showcase)
                clickableConstraintLayout.setOnClickListener { listener.onItemClicked(mode.id) }
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

interface CityAdapterItemListener {
    fun onItemClicked(modeId: String)
}
