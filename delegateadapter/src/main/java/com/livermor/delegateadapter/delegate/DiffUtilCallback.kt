package com.livermor.delegateadapter.delegate

import androidx.recyclerview.widget.DiffUtil

/**
 * @author dumchev on 16.11.17.
 */
class DiffUtilCallback(
    private val oldState: AdaptersState,
    private val newState: AdaptersState
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldState.data.size
    override fun getNewListSize(): Int = newState.data.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldState.itemId(oldItemPosition) == newState.itemId(newItemPosition)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldState.itemContent(oldItemPosition) == newState.itemContent(newItemPosition)

    private fun AdaptersState.itemId(position: Int): Any =
        getAdapterByItemPosition(position).itemId(data[position])

    private fun AdaptersState.itemContent(position: Int): Any =
        getAdapterByItemPosition(position).itemContent(data[position])
}