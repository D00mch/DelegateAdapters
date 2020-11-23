package com.livermor.delegateadapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ViewBindingDelegateAdapter<T : Any, V : ViewBinding>(
    private val viewBindingInflater: (LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> V
) : DelegateAdapter {

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = viewBindingInflater.invoke(layoutInflater, parent, false)
        viewBinding.onCreated()
        return ViewBindingHolder(
            viewBinding
        )
    }

    open fun V.onCreated() {}

    @Suppress("UNCHECKED_CAST")
    final override fun onBindViewHolder(holder: RecyclerView.ViewHolder, items: List<Any>, position: Int) {
        holder as ViewBindingHolder<V>
        holder.viewBinding.onBind(items[position] as T)
    }

    abstract fun V.onBind(item: T)

    override fun onRecycled(holder: RecyclerView.ViewHolder) {
        (holder as ViewBindingHolder<V>).viewBinding.onRecycled()
    }

    open fun V.onRecycled() {}

    abstract fun isForViewType(item: Any): Boolean

    abstract fun T.getItemId(): Any

    override fun itemContent(item: Any): Any = item

    @Suppress("UNCHECKED_CAST")
    final override fun itemId(item: Any): Any = (item as T).getItemId()

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return isForViewType(items[position])
    }

    @Suppress("UNCHECKED_CAST")
    final override fun onAttachedToWindow(holder: RecyclerView.ViewHolder)
        = (holder as ViewBindingHolder<V>).viewBinding.onAttachedToWindow()

    @Suppress("UNCHECKED_CAST")
    final override fun onDetachedFromWindow(holder: RecyclerView.ViewHolder)
        = (holder as ViewBindingHolder<V>).viewBinding.onDetachedFromWindow()

    open fun V.onAttachedToWindow() {}
    open fun V.onDetachedFromWindow() {}

    private class ViewBindingHolder<V : ViewBinding>(
        val viewBinding: V
    ) : RecyclerView.ViewHolder(viewBinding.root)
}
