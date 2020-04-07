package com.livermor.dumchev.delegateadapters.base.adapter

import com.livermor.delegateadapter.delegate.KDelegateAdapter
import com.livermor.dumchev.delegateadapters.R
import com.livermor.dumchev.delegateadapters.base.model.CheckItem
import kotlinx.android.synthetic.main.check_item.*

/**
 * @author dumchev on 05.11.17.
 */
class CheckDelegateAdapter : KDelegateAdapter<CheckItem>() {

    override fun KViewHolder.onBind(item: CheckItem) =
        with(check_box) {
            text = item.title
            isChecked = item.isChecked
            setOnCheckedChangeListener { _, isChecked ->
                item.isChecked = isChecked
            }
        }

    override fun onRecycled(viewHolder: KViewHolder) {
        viewHolder.check_box.setOnCheckedChangeListener(null)
    }

    override fun isForViewType(item: Any) = item is CheckItem
    override fun getLayoutId(): Int = R.layout.check_item
}