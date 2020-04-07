package com.livermor.dumchev.delegateadapters.base.model

import com.livermor.delegateadapter.delegate.diff.KDiffUtilItem

/**
 * @author dumchev on 11/12/2018.
 */
data class CompositeModel(
    val imageViewModel: ImageItem,
    val textViewModel: TextItem
) : KDiffUtilItem {

    override val id: Any = "" + imageViewModel.id() + textViewModel.id()
    override fun content(): Any = this
}