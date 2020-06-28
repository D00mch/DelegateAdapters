package com.livermor.dumchev.delegateadapters.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.livermor.delegateadapter.delegate.CompositeDelegateAdapter
import com.livermor.dumchev.delegateadapters.R
import com.livermor.dumchev.delegateadapters.base.adapter.CheckDelegateAdapter
import com.livermor.dumchev.delegateadapters.base.adapter.GenerateItemsDelegateAdapter
import com.livermor.dumchev.delegateadapters.base.adapter.TxtDelegateAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author dumchev on 28.11.17.
 */
class BaseExampleActivity : AppCompatActivity() {

    private val adapter = CompositeDelegateAdapter(
        TxtDelegateAdapter(),
        CheckDelegateAdapter(),
        GenerateItemsDelegateAdapter { generateNewData() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_example)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        adapter.swapData(MockDataFactory.prepareData())
    }

    fun onGenerateButtonClicked(@Suppress("UNUSED_PARAMETER") view: View?) {
        generateNewData()
    }

    private fun generateNewData() {
        adapter.swapData(MockDataFactory.prepareData())
        rv.scrollToPosition(0)
    }
}