package com.livermor.dumchev.delegateadapters.base.adapter

import android.view.View
import com.livermor.delegateadapter.delegate.KDelegateAdapter
import com.livermor.dumchev.delegateadapters.R
import com.livermor.dumchev.delegateadapters.base.model.ConcatenetedModel

/**
 * @author dumchev on 11/12/2018.
 */
class ConcatenatingAdapter(clicks: View.OnClickListener) : KDelegateAdapter<ConcatenetedModel>() {

    private val textDelegateAdapter by lazy { TxtDelegateAdapter() }
    private val imageDelegateAdapter by lazy { ImageDelegateAdapter(clicks) }

    override fun KViewHolder.onBind(item: ConcatenetedModel) {
        with(textDelegateAdapter) { onBind(item.textViewModel) }
        with(imageDelegateAdapter) { onBind(item.imageViewModel) }
    }

    override fun getLayoutId(): Int = R.layout.composite_item

    override fun isForViewType(item: Any): Boolean = item is ConcatenetedModel
}