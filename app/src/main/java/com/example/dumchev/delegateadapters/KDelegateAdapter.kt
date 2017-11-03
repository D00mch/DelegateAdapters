package com.example.dumchev.delegateadapters

import android.view.View
import com.example.delegateadapter.delegate.SimpleDelegateAdapter
import kotlinx.android.extensions.LayoutContainer

/**
 * @author dumchev on 04.11.17.
 */
abstract class KDelegateAdapter<T> : SimpleDelegateAdapter<T>(), LayoutContainer {

    override var containerView: View? = null

    abstract fun onInflateItem(view: View, item: T)

    final override fun inflated(view: View, item: T) {
        containerView = view
        onInflateItem(view, item)
    }
}