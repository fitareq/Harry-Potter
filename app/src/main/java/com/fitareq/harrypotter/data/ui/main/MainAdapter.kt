package com.fitareq.harrypotter.data.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fitareq.harrypotter.R
import com.fitareq.harrypotter.data.models.Character
import com.fitareq.harrypotter.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class MainAdapter(
    private val items: List<Character>,
    val callBack: (Character) -> Unit
) : RecyclerView.Adapter<MainAdapter.mViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        return mViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val item = items[position]
        holder.binding.profileImage.clipToOutline = true
        item.image?.let {
            if (it.isNotEmpty())
                Picasso.get().load(it).placeholder(R.drawable.placeholder).into(holder.binding.profileImage)
        }
        item.name?.let {
            holder.binding.profileName.text = it
        }
        item.house?.let {
            holder.binding.houseName.text = it
        }?: run { holder.binding.houseName.text = "No house" }

        holder.itemView.setOnClickListener {
            callBack(item)
        }
    }

    inner class mViewHolder(val binding: ItemCharacterBinding) :
        ViewHolder(binding.root)
}