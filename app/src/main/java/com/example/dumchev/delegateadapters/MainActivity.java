package com.example.dumchev.delegateadapters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.delegateadapter.delegate.CompositeDelegateAdapter;
import com.example.dumchev.delegateadapters.adapter.CheckDelegateAdapter;
import com.example.dumchev.delegateadapters.adapter.ImageDelegateAdapter;
import com.example.dumchev.delegateadapters.adapter.TextDelegateAdapter;
import com.example.dumchev.delegateadapters.model.IViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        View.OnClickListener onImageClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "image item clicked", Toast.LENGTH_LONG).show();
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
