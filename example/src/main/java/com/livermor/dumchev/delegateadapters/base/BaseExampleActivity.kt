package com.livermor.dumchev.delegateadapters.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.livermor.delegateadapter.delegate.CompositeDelegateAdapter
import com.livermor.dumchev.delegateadapters.base.adapter.CheckDelegateAdapter
import com.livermor.dumchev.delegateadapters.base.adapter.GenerateItemsDelegateAdapter
import com.livermor.dumchev.delegateadapters.base.adapter.TxtDelegateAdapter
import com.livermor.dumchev.delegateadapters.databinding.ActivityBaseExampleBinding

/**

@author dumchev on 28.11.17.
 */
class BaseExampleActivity : AppCompatActivity() {

    private val adapter = CompositeDelegateAdapter(
        TxtDelegateAdapter(),
        CheckDelegateAdapter(),
        GenerateItemsDelegateAdapter { generateNewData() }
    )
    private lateinit var binding: ActivityBaseExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter
        adapter.swapData(MockDataFactory.prepareData())
    }

    fun onGenerateButtonClicked(@Suppress("UNUSED_PARAMETER") view: View?) {
        generateNewData()
    }

    private fun generateNewData() {
        adapter.swapData(MockDataFactory.prepareData())
        binding.rv.scrollToPosition(0)
    }
}
