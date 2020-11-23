package com.livermor.dumchev.delegateadapters.base.adapter

import android.view.View
import com.livermor.delegateadapter.delegate.ViewBindingDelegateAdapter
import com.livermor.dumchev.delegateadapters.base.ImageItem
import com.livermor.dumchev.delegateadapters.databinding.ImageItemBinding

/**
 * @author dumchev on 04.11.17.
 */
class ImageDelegateAdapter(private val clickListener: View.OnClickListener) :
    ViewBindingDelegateAdapter<ImageItem, ImageItemBinding>(ImageItemBinding::inflate) {

    override fun ImageItemBinding.onBind(item: ImageItem) {
        tvTitle.text = item.title
        imgBg.setOnClickListener(clickListener)
        imgBg.setImageResource(item.imageRes)
    }

    override fun isForViewType(item: Any): Boolean = item is ImageItem

    override fun ImageItem.getItemId(): Any = title
}