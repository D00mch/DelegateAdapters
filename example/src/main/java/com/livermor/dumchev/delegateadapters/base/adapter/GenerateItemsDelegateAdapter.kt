package com.livermor.dumchev.delegateadapters.base.adapter

import com.livermor.delegateadapter.delegate.ViewBindingDelegateAdapter
import com.livermor.dumchev.delegateadapters.base.ImageItem
import com.livermor.dumchev.delegateadapters.databinding.ImageItemBinding

class GenerateItemsDelegateAdapter(private val generateNewItems: () -> Unit) :
    ViewBindingDelegateAdapter<ImageItem, ImageItemBinding>(ImageItemBinding::inflate) {

    override fun ImageItemBinding.onBind(item: ImageItem) {
        tvTitle.text = item.title
        imgBg.setImageResource(item.imageRes)
        llRoot.setOnClickListener { generateNewItems() }
    }

    override fun isForViewType(item: Any): Boolean = item is ImageItem

    override fun ImageItem.getItemId(): Any = title
}