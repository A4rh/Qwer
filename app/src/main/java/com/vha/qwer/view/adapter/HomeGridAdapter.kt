package com.vha.qwer.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.vha.qwer.databinding.ItemHomeGridlistBinding
import com.vha.qwer.model.HomeItem

class HomeGridAdapter(private val mContext: Context) :
    BaseQuickAdapter<HomeItem, HomeGridAdapter.ItemHolder>(0) {

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = ItemHomeGridlistBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ItemHolder(binding)
    }

    override fun convert(holder: ItemHolder, item: HomeItem) {
        holder.binding.imageView.setImageResource(item.imgDrawableRes)
        holder.binding.textView.text = item.itemText
    }

    class ItemHolder(binding: ItemHomeGridlistBinding) : BaseViewHolder(binding.root) {
        var binding: ItemHomeGridlistBinding

        init {
            this.binding = binding
        }
    }
}