package com.livermor.dumchev.delegateadapters.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.livermor.delegateadapter.delegate.diff.DiffUtilCompositeAdapter;
import com.livermor.dumchev.delegateadapters.R;
import com.livermor.dumchev.delegateadapters.base.adapter.CheckDelegateAdapter;
import com.livermor.dumchev.delegateadapters.base.adapter.ImageDelegateAdapter;
import com.livermor.dumchev.delegateadapters.base.adapter.TextDelegateAdapter;

import static android.widget.Toast.LENGTH_LONG;

/**
 * @author dumchev on 28.11.17.
 */
public class BaseExampleActivity extends AppCompatActivity {

    private DiffUtilCompositeAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_example);

        View.OnClickListener onImageClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BaseExampleActivity.this, "image item clicked", LENGTH_LONG).show();
            }
        };

        adapter = new DiffUtilCompositeAdapter
            .Builder()
            .add(new ImageDelegateAdapter(onImageClick))
            .add(new TextDelegateAdapter())
            .add(new CheckDelegateAdapter())
            .build();

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.swapData(MockDataFactory.prepareData());
    }


    public void onGenerateButtonClicked(View view) {
        adapter.swapData(MockDataFactory.prepareData());
        recyclerView.scrollToPosition(0);
    }
}
