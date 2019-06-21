package com.example.dumchev.delegateadapters.base.adapter

import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.dumchev.delegateadapters.R
import com.example.dumchev.delegateadapters.base.model.ImageViewModel
import kotlinx.android.synthetic.main.image_item.*

class GenerateItemsDelegateAdapter(private val generateNewItems: () -> Unit) :
        KDelegateAdapter<ImageViewModel>() {

    override fun onBind(item: ImageViewModel, viewHolder: KViewHolder) = with(viewHolder) {
        tv_title.text = item.title
        img_bg.setImageResource(item.imageRes)
        itemView.setOnClickListener { generateNewItems() }
    }

    override fun getLayoutId(): Int = R.layout.image_item

    override fun isForViewType(items: List<*>, position: Int) = items[position] is ImageViewModel
}