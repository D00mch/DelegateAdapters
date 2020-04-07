package com.livermor.dumchev.delegateadapters.base.adapter

import com.livermor.delegateadapter.delegate.KDelegateAdapter
import com.livermor.dumchev.delegateadapters.R
import com.livermor.dumchev.delegateadapters.base.model.ImageItem
import kotlinx.android.synthetic.main.image_item.*

class GenerateItemsDelegateAdapter(private val generateNewItems: () -> Unit) :
        KDelegateAdapter<ImageItem>() {

    override fun KViewHolder.onBind(item: ImageItem) {
        tv_title.text = item.title
        img_bg.setImageResource(item.imageRes)
        itemView.setOnClickListener { generateNewItems() }
    }

    override fun getLayoutId(): Int = R.layout.image_item

    override fun isForViewType(item: Any): Boolean = item is ImageItem
}