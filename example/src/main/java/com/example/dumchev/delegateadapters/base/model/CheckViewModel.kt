package com.example.dumchev.delegateadapters.base.model

import com.example.delegateadapter.delegate.CompositeDelegateAdapter
import com.example.dumchev.delegateadapters.base.adapter.CheckDelegateAdapter

/**
 * @author dumchev on 05.11.17.
 */
class CheckViewModel(val title: String, var isChecked: Boolean): IViewModel
fun f () {
    val newAdapter = CompositeDelegateAdapter.Builder<IViewModel>()
            .add(CheckDelegateAdapter())
            .build()
}