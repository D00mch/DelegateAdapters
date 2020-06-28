package com.livermor.delegateadapter.delegate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.extensions.LayoutContainer

/**
 * @author dumchev on 03.11.17.
 */
abstract class KDelegateAdapter<T : Any> : DelegateAdapter {

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun isForViewType(item: Any): Boolean

    abstract fun KViewHolder.onBind(item: T)

    abstract fun T.getItemId(): Any

    open fun T.getItemContent(): Any = this

    open fun createView(parent: ViewGroup): View =
        LayoutInflater.from(parent.context).inflate(getLayoutId(), parent, false)

    open fun onCreated(view: View) = Unit

    override fun isForViewType(items: List<Any>, position: Int): Boolean = isForViewType(items[position])

    override fun onRecycled(holder: ViewHolder) {
    }

    override fun onBindViewHolder(holder: ViewHolder, items: List<Any>, position: Int) {
        (holder as KViewHolder).bind(items[position])
    }

    @Suppress("UNCHECKED_CAST")
    final override fun itemId(item: Any): Any = (item as T).getItemId()

    @Suppress("UNCHECKED_CAST")
    final override fun itemContent(item: Any): Any = (item as T).getItemContent()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = KViewHolder(
        containerView = createView(parent),
        onCreated = ::onCreated
    ).apply {
        onInflated = { viewType: Any -> @Suppress("UNCHECKED_CAST") onBind(viewType as T) }
    }

    class KViewHolder(
        override val containerView: View,
        onCreated: (View) -> Unit
    ) : ViewHolder(containerView), LayoutContainer {
        var onInflated: (viewType: Any) -> Unit? = { throw IllegalArgumentException("onInflated not specified") }

        init {
            onCreated(containerView)
        }

        internal fun bind(item: Any) = onInflated(item)
    }
}