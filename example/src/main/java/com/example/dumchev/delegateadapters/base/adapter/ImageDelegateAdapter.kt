package com.example.dumchev.delegateadapters.base.adapter

import android.view.View
import com.example.dumchev.delegateadapters.KDelegateAdapter
import com.example.dumchev.delegateadapters.R
import com.example.dumchev.delegateadapters.base.model.ImageViewModel
import kotlinx.android.synthetic.main.image_item.*

/**
 * @author dumchev on 04.11.17.
 */
class ImageDelegateAdapter(private val clickListener: View.OnClickListener)
    : KDelegateAdapter<ImageViewModel>() {

    override fun onBind(item: ImageViewModel, viewHolder: KViewHolder) =
            with(viewHolder) {
                tv_title.text = item.title
                img_bg.setOnClickListener(clickListener)
                img_bg.setImageResource(item.imageRes)
            }

    override fun isForViewType(items: List<*>, position: Int) =
            items[position] is ImageViewModel

    override fun getLayoutId(): Int = R.layout.image_item
}