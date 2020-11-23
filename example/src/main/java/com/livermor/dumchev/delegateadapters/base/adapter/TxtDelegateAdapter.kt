package com.livermor.dumchev.delegateadapters.base.adapter

import com.livermor.delegateadapter.delegate.ViewBindingDelegateAdapter
import com.livermor.dumchev.delegateadapters.base.TextItem
import com.livermor.dumchev.delegateadapters.databinding.TextItemBinding

/**
 * @author dumchev on 05.11.17.
 */
class TxtDelegateAdapter : ViewBindingDelegateAdapter<TextItem, TextItemBinding>(TextItemBinding::inflate) {

    override fun TextItemBinding.onBind(item: TextItem) {
        tvTitle.text = item.title
        tvDescription.text = item.description
    }

    override fun isForViewType(item: Any) = item is TextItem

    override fun TextItem.getItemId(): Any = title
}