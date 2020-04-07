package com.livermor.delegateadapter.delegate;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author dumchev on 03.11.17.
 */
public interface DelegateAdapter<VH extends RecyclerView.ViewHolder, T> {

    @NonNull
    RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    void onBindViewHolder(@NonNull VH holder,
                          @NonNull List<T> items,
                          int position);

    void onRecycled(@NonNull VH holder);

    boolean isForViewType(@NonNull List<Object> items, int position);
}
