package com.example.dumchev.delegateadapters.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.delegateadapter.delegate.CompositeDelegateAdapter;
import com.example.dumchev.delegateadapters.R;
import com.example.dumchev.delegateadapters.base.adapter.CheckDelegateAdapter;
import com.example.dumchev.delegateadapters.base.adapter.ImageDelegateAdapter;
import com.example.dumchev.delegateadapters.base.adapter.TextDelegateAdapter;
import com.example.dumchev.delegateadapters.base.model.IViewModel;

import static android.widget.Toast.LENGTH_LONG;

/**
 * @author dumchev on 28.11.17.
 */

public class BaseExampleActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_example);
        RecyclerView recyclerView = findViewById(R.id.rv);
        View.OnClickListener onImageClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseExampleActivity.this, "image item clicked", LENGTH_LONG).show();
            }
        };
        CompositeDelegateAdapter<IViewModel> adapter = new CompositeDelegateAdapter
            .Builder<IViewModel>()
            .add(new ImageDelegateAdapter(onImageClick))
            .add(new TextDelegateAdapter())
            .add(new CheckDelegateAdapter())
            .build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.swapData(MockDataFactory.prepareData());
    }
}
