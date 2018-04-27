package com.example.delegateadapter.delegate

import android.view.View
import kotlinx.android.extensions.LayoutContainer

/**
 * @author dumchev on 04.11.17.
 */
abstract class KDelegateAdapter<T>
    : BaseDelegateAdapter<KDelegateAdapter.KViewHolder, T>() {

    open fun onCreated(view: View) = Unit

    abstract fun onBind(item: T, viewHolder: KViewHolder)

    final override fun onBindViewHolder(view: View, item: T, viewHolder: KViewHolder) {
        onBind(item, viewHolder)
    }

    override fun createViewHolder(parent: View): KViewHolder {
        return KViewHolder(parent, ::onCreated)
    }

    class KViewHolder(override val containerView: View, onCreated: (View) -> Unit)
        : BaseViewHolder(containerView), LayoutContainer {

        init {
            onCreated(containerView)
        }
    }
}