package com.example.dumchev.delegateadapters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.delegateadapter.delegate.CompositeDelegateAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        Function1<ImageViewModel, Unit> onImageClick = new Function1<ImageViewModel, Unit>() {
            @Override
            public Unit invoke(ImageViewModel imageViewModel) {
                Toast.makeText(MainActivity.this, "image item clicked", Toast.LENGTH_LONG).show();
                return null;
            }
        };
        CompositeDelegateAdapter adapter = new CompositeDelegateAdapter.Builder()
            .add(new ImageDelegateAdapter(onImageClick))
            .add(new TextDelegateAdapter())
            .build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setData(prepareData());
    }

    private List<Object> prepareData() {
        ArrayList<Object> objects = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Object item;
            if (random.nextBoolean()) {
                item = new TextViewModel("Title " + i, "Description " + i);
            } else {
                item = new ImageViewModel("Title " + i, R.mipmap.ic_launcher_round);
            }
            objects.add(item);
        }
        objects.add(1); // to check that we have warning for item without adapter
        return objects;
    }
}
