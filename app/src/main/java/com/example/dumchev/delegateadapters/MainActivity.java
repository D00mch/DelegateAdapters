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
        CompositeDelegateAdapter adapter = new CompositeDelegateAdapter.Builder()
            .add(new ImageDelegateAdapter(new Function1<ImageViewModel, Unit>() {
                @Override
                public Unit invoke(ImageViewModel imageViewModel) {
                    Toast.makeText(MainActivity.this, "image item clicked", Toast.LENGTH_LONG);
                    return null;
                }
            }))
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
                item = new TextViewModel("title " + i, "description " + i);
            } else {
                item = new ImageViewModel("title " + i, R.mipmap.ic_launcher_round);
            }
            objects.add(item);
        }
        return objects;
    }
}
