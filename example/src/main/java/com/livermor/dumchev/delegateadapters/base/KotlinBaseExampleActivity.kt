package com.livermor.dumchev.delegateadapters.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.livermor.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import com.livermor.dumchev.delegateadapters.base.adapter.CheckDelegateAdapter
import com.livermor.dumchev.delegateadapters.base.adapter.CompositeDelegateAdapter
import com.livermor.dumchev.delegateadapters.base.adapter.GenerateItemsDelegateAdapter
import com.livermor.dumchev.delegateadapters.base.adapter.TextDelegateAdapter
import kotlinx.android.synthetic.main.activity_base_example.*

class KotlinBaseExampleActivity : AppCompatActivity() {

    private val diffAdapter by lazy {
        DiffUtilCompositeAdapter.Builder()
                .add(GenerateItemsDelegateAdapter { generateNewData() })
                .add(TextDelegateAdapter())
                .add(CheckDelegateAdapter())
                .add(CompositeDelegateAdapter(View.OnClickListener { generateNewData() }))
                .build()
    }

    private fun generateNewData() {
        diffAdapter.swapData(MockDataFactory.prepareData())
        rv.scrollToPosition(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.livermor.dumchev.delegateadapters.R.layout.activity_base_example)
        rv.run {
            layoutManager = LinearLayoutManager(this@KotlinBaseExampleActivity)
            adapter  = diffAdapter
            generateNewData()
        }
    }

    fun onGenerateButtonClicked(view: View) {
        diffAdapter.swapData(MockDataFactory.prepareData())
        rv.scrollToPosition(0)
    }
}