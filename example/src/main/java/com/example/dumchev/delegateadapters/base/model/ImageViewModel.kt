package com.example.dumchev.delegateadapters.base.model

import android.support.annotation.DrawableRes

import com.example.delegateadapter.delegate.diff.IComparableItem

/**
 * @author dumchev on 05.11.17.
 */
class ImageViewModel(val title: String, @DrawableRes val imageRes: Int) : IComparableItem {

    override fun id(): Any = title
    override fun content(): Any = title + imageRes
}
