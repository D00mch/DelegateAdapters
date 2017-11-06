package com.example.dumchev.delegateadapters.adapter

import android.view.View
import com.example.delegateadapter.delegate.BaseDelegateAdapter
import com.example.delegateadapter.delegate.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer

/**
 * @author dumchev on 04.11.17.
 */
abstract class KDelegateAdapter<T>
    : BaseDelegateAdapter<KDelegateAdapter.KViewHolder, T>() {

    abstract fun onBind(item: T, viewHolder: KViewHolder)

    final override fun onBindViewHolder(view: View, item: T, viewHolder: KViewHolder) {
        onBind(item, viewHolder)
    }

    override fun createViewHolder(parent: View?): KViewHolder {
        return KViewHolder(parent)
    }

    class KViewHolder(override val containerView: View?)
        : BaseViewHolder(containerView), LayoutContainer
}