package com.livermor.dumchev.delegateadapters.base.model

import androidx.annotation.DrawableRes
import com.livermor.delegateadapter.delegate.diff.DiffUtilItem
import com.livermor.delegateadapter.delegate.diff.KDiffUtilItem

/**
 * @author dumchev on 05.11.17.
 */
data class ImageItem(val title: String, @DrawableRes val imageRes: Int) : KDiffUtilItem {
    override val id: Any = title
}
