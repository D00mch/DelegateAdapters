package com.example.delegateadapter.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author dumchev on 03.11.17.
 */
public interface IDelegateAdapter<VH extends RecyclerView.ViewHolder, T> {

    @NonNull
    RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    void onBindViewHolder(@NonNull VH holder,
                          @NonNull List<T> items,
                          int position);

    void onRecycled(VH holder);

    boolean isForViewType(@NonNull List<?> items, int position);
}
