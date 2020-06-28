package com.livermor.dumchev.delegateadapters.base.adapter

import android.view.View
import com.livermor.delegateadapter.delegate.KDelegateAdapter
import com.livermor.dumchev.delegateadapters.R
import com.livermor.dumchev.delegateadapters.base.ImageItem
import kotlinx.android.synthetic.main.image_item.*

/**
 * @author dumchev on 04.11.17.
 */
class ImageDelegateAdapter(private val clickListener: View.OnClickListener) : KDelegateAdapter<ImageItem>() {

    override fun KViewHolder.onBind(item: ImageItem) {
        tv_title.text = item.title
        img_bg.setOnClickListener(clickListener)
        img_bg.setImageResource(item.imageRes)
    }

    override fun isForViewType(item: Any) = item is ImageItem
    override fun getLayoutId(): Int = R.layout.image_item
    override fun ImageItem.getItemId(): Any = title
}