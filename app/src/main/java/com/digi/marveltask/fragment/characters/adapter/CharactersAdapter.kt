package com.digi.marveltask.fragment.characters.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.digi.domain.entity.Result
import com.digi.marveltask.R
import com.digi.marveltask.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class CharactersAdapter(private val listener: RecyclerViewEvent) :
    PagingDataAdapter<Result, CharactersAdapter.ViewHolder>(DiffCallback()) {
    override fun onBindViewHolder(holder: CharactersAdapter.ViewHolder, position: Int) {

        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactersAdapter.ViewHolder {
        val itemBinding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    inner class ViewHolder(private val itemBinding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {
        fun bind(character: Result) {
            val image = character.thumbnail?.path.plus(".").plus(character.thumbnail?.extension)
            Log.e("Image ", image)
            Picasso.get().load(image.replace("http", "https"))
                .placeholder(R.drawable.marvel_logo).fit().centerCrop().into(itemBinding.characterIv)
            itemBinding.titleTv.text = character.name
        }

        init {
            itemBinding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                getItem(position)?.let { listener.onItemClick(it) }
            }
        }
    }





class DiffCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(
        oldItem: Result,
        newItem: Result
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Result,
        newItem: Result
    ): Boolean {
        return oldItem == newItem
    }
}

interface RecyclerViewEvent {
    fun onItemClick(character: Result)
}
}