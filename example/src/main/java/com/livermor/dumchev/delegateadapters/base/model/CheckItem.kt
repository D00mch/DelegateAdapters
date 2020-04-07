package com.livermor.dumchev.delegateadapters.base.model

import com.livermor.delegateadapter.delegate.diff.KDiffUtilItem

/**
 * @author dumchev on 05.11.17.
 */
data class CheckItem(val title: String, var isChecked: Boolean) : KDiffUtilItem {
    override val id: Any = title
}

