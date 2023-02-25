package com.example.myreffapp2023.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myreffapp2023.Constants.ITEMS
import com.example.myreffapp2023.Constants.NO_ITEM
import com.example.myreffapp2023.R
import com.example.myreffapp2023.databinding.CardViewItemBinding
import com.example.myreffapp2023.models.PhotoModel
import com.squareup.picasso.Picasso

class ItemListAdapter(
    private val itemList: ArrayList<PhotoModel>,
    private val selectedItem: (itemId: Int?) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ItemListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = CardViewItemBinding.bind(itemView)
    }

    inner class NoItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEMS -> {
                ItemListViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.card_view_item,
                        parent,
                        false
                    )
                )
            }
            else -> {
                NoItemHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.card_view_no_item,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemListViewHolder) {
            holder.binding.itemId.text = "#${itemList[position].id}"
            holder.binding.title.text = "${itemList[position].title}"
            holder.binding.albumId.text = "Album: ${itemList[position].albumId}"
            holder.binding.itemLayout.setOnClickListener {
                selectedItem(itemList[position].id)
            }

            Picasso.get()
                .load(itemList[position].url)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_error)
                .centerCrop()
                .fit()
                .into(holder.binding.imageView)
        }
    }

    fun updatePhotoList(list: List<PhotoModel>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return if (itemList.isNotEmpty()) {
            return itemList.size
        } else {
            NO_ITEM
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemList.isNotEmpty()) {
            ITEMS
        } else {
            NO_ITEM
        }
    }
}