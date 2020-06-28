package com.livermor.dumchev.delegateadapters.base.adapter

import com.livermor.delegateadapter.delegate.KDelegateAdapter
import com.livermor.dumchev.delegateadapters.R
import com.livermor.dumchev.delegateadapters.base.TextItem
import kotlinx.android.synthetic.main.text_item.*

/**
 * @author dumchev on 05.11.17.
 */
class TxtDelegateAdapter : KDelegateAdapter<TextItem>() {

    override fun KViewHolder.onBind(item: TextItem) {
        tv_title.text = item.title
        tv_description.text = item.description
    }

    override fun isForViewType(item: Any) = item is TextItem
    override fun getLayoutId(): Int = R.layout.text_item

    override fun TextItem.getItemId(): Any = title
}