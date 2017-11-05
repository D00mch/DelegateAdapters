package com.example.delegateadapter.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author dumchev on 03.11.17.
 */
public interface IDelegateAdapter {

    @NonNull
    RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,
                          @NonNull List<? extends Object> items,
                          int position);

    boolean isForViewType(@NonNull List<? extends Object> items, int position);
}
