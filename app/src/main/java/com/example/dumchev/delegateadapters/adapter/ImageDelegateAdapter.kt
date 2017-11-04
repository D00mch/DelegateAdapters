package com.example.dumchev.delegateadapters.adapter

import com.example.dumchev.delegateadapters.R
import com.example.dumchev.delegateadapters.model.ImageViewModel
import kotlinx.android.synthetic.main.image_item.*

/**
 * @author dumchev on 04.11.17.
 */
class ImageDelegateAdapter(private val onImageClick: (ImageViewModel) -> Unit)
    : KDelegateAdapter<ImageViewModel>() {

    override fun onInflated(item: ImageViewModel, viewHolder: KViewHolder)= with(viewHolder) {
        tv_title.text = item.title
        img_bg.setOnClickListener { onImageClick(item) }
        img_bg.setImageResource(item.imageRes)
    }

    override fun isForViewType(items: List<Any>, position: Int) = items[position] is ImageViewModel

    override fun getLayoutId(): Int = R.layout.image_item
}