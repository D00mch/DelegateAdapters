package com.example.dumchev.delegateadapters.base.adapter

import android.view.View
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.dumchev.delegateadapters.R
import com.example.dumchev.delegateadapters.base.model.CompositeModel

/**
 * @author dumchev on 11/12/2018.
 */
class CompositeDelegateAdapter(clicks: View.OnClickListener) : KDelegateAdapter<CompositeModel>() {

    val textDelegateAdapter by lazy { TxtDelegateAdapter() }
    val imageDelegateAdapter by lazy { ImageDelegateAdapter(clicks) }

    override fun onBind(item: CompositeModel, viewHolder: KViewHolder) {
        textDelegateAdapter.onBind(item.textViewModel, viewHolder)
        imageDelegateAdapter.onBind(item.imageViewModel, viewHolder)
    }

    override fun getLayoutId(): Int = R.layout.composite_item

    override fun isForViewType(items: MutableList<*>, position: Int): Boolean {
        return items[position] is CompositeModel
    }
}