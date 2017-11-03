package com.example.dumchev.delegateadapters

import android.view.View
import com.example.delegateadapter.delegate.SimpleDelegateAdapter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.image_item.view.*

/**
 * @author dumchev on 04.11.17.
 */
class ImageDelegateAdapter(private val onImageClick: (ImageViewModel) -> Unit)
    : KDelegateAdapter<ImageViewModel>() {

    override fun onInflateItem(view: View, item: ImageViewModel) = with(view) {
        setOnClickListener { onImageClick(item) }
        tv_title.text = item.title
        img_bg.setImageResource(item.imageRes)
    }

    override fun isForViewType(items: List<Any>, position: Int) = items[position] is ImageViewModel

    override fun getLayoutId(): Int = R.layout.image_item
}