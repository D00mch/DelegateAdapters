package com.example.dumchev.delegateadapters.base.model

import com.example.delegateadapter.delegate.diff.IComparableItem

/**
 * @author dumchev on 05.11.17.
 */
class CheckViewModel(val title: String, var isChecked: Boolean) : IComparableItem {
    override fun id(): Any = title
    override fun content(): Any = title + isChecked
}

