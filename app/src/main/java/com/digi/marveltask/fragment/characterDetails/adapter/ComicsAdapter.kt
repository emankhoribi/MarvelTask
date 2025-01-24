package com.digi.marveltask.fragment.characterDetails.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.digi.domain.entity.comicapi.ComicResult
import com.digi.marveltask.R
import com.digi.marveltask.databinding.ItemComicBinding
import com.squareup.picasso.Picasso

class ComicsAdapter(private val listener: RecyclerViewEvent) :
    ListAdapter<ComicResult, ComicsAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemComicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }


    inner class ViewHolder(private val itemBinding: ItemComicBinding) :
        RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {
        fun bind(comic: ComicResult) {
            val image = (comic.thumbnail?.path.plus(".").plus(comic.thumbnail?.extension)).replace("http", "https")
            Picasso.get().load(image).fit().placeholder(R.drawable.ic_placeholder).into(itemBinding.comicIv)
        }

        init {
            itemBinding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(currentList, position)
            }
        }

    }


    class DiffCallback : DiffUtil.ItemCallback<ComicResult>() {
        override fun areItemsTheSame(
            oldItem: ComicResult,
            newItem: ComicResult
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ComicResult,
            newItem: ComicResult
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface RecyclerViewEvent {
        fun onItemClick(comicList: MutableList<ComicResult>, position: Int)
    }
}