package com.example.dumchev.delegateadapters.adapter

import com.example.dumchev.delegateadapters.R
import com.example.dumchev.delegateadapters.model.CheckViewModel
import kotlinx.android.synthetic.main.check_item.*

/**
 * @author dumchev on 05.11.17.
 */
class CheckDelegateAdapter : KDelegateAdapter<CheckViewModel>() {
    override fun onInflated(item: CheckViewModel, viewHolder: KViewHolder) =
            with(viewHolder.check_box) {
                text = item.title
                setOnCheckedChangeListener(null)
                isChecked = item.isChecked
                setOnCheckedChangeListener { _, isChecked ->
                    item.isChecked = isChecked
                }
            }

    override fun isForViewType(items: List<out Any>, position: Int) =
            items[position] is CheckViewModel

    override fun getLayoutId(): Int = R.layout.check_item
}