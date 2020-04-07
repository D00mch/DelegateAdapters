package com.livermor.delegateadapter.delegate

import android.view.View
import kotlinx.android.extensions.LayoutContainer

/**
 * @author dumchev on 04.11.17.
 */
abstract class KDelegateAdapter<T>
    : BaseDelegateAdapter<KDelegateAdapter.KViewHolder, T>() {

    open fun onCreated(view: View) = Unit

    abstract fun KViewHolder.onBind(item: T)

    abstract fun isForViewType(item: Any): Boolean

    override fun isForViewType(items: List<Any>, position: Int): Boolean = isForViewType(items[position])

    final override fun onBindViewHolder(view: View, item: T, viewHolder: KViewHolder) = viewHolder.onBind(item)

    override fun createViewHolder(parent: View): KViewHolder = KViewHolder(parent, ::onCreated)

    class KViewHolder(override val containerView: View, onCreated: (View) -> Unit) : BaseViewHolder(containerView),
        LayoutContainer {

        init {
            onCreated(containerView)
        }
    }
}