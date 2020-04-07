package com.example.dumchev.delegateadapters.base.model

import com.example.delegateadapter.delegate.diff.IComparableItem

/**
 * @author dumchev on 11/12/2018.
 */
data class CompositeModel(
    val imageViewModel: ImageViewModel,
    val textViewModel: TextViewModel
) : IComparableItem {

    override fun id(): Any = "" + imageViewModel.id() + textViewModel.id()
    override fun content(): Any = this
}