package com.example.dumchev.delegateadapters.adapter

import com.example.dumchev.delegateadapters.R
import com.example.dumchev.delegateadapters.model.TextViewModel
import kotlinx.android.synthetic.main.text_item.*

/**
 * @author dumchev on 05.11.17.
 */
class TxtDelegateAdapter : KDelegateAdapter<TextViewModel>() {

    override fun onBind(item: TextViewModel, viewHolder: KViewHolder) =
            with(viewHolder) {
                tv_title.text = item.title
                tv_description.text = item.description
            }

    override fun isForViewType(items: List<*>, position: Int) =
            items[position] is TextViewModel

    override fun getLayoutId(): Int = R.layout.text_item
}