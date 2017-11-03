package com.example.delegateadapter.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dumchev on 03.11.17.
 */

public class CompositeDelegateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = CompositeDelegateAdapter.class.getSimpleName();
    private static final int EMPTY_VIEW_TYPE = -1;

    private final SparseArray<IDelegateAdapter> typeToAdapterMap;
    private @NonNull List<Object> data = new ArrayList<>();

    private CompositeDelegateAdapter(@NonNull SparseArray<IDelegateAdapter> typeToAdapterMap) {
        this.typeToAdapterMap = typeToAdapterMap;
    }

    @Override
    public int getItemViewType(int position) {
        for (int i = EMPTY_VIEW_TYPE + 1; i < typeToAdapterMap.size(); i++) {
            IDelegateAdapter delegate = typeToAdapterMap.valueAt(i);
            if (delegate.isForViewType(data, position)) {
                return typeToAdapterMap.keyAt(i);
            }
        }
        return EMPTY_VIEW_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW_TYPE) return new EmptyViewHolder(new View(parent.getContext()));
        return typeToAdapterMap.get(viewType).onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IDelegateAdapter delegateAdapter = typeToAdapterMap.get(getItemViewType(position));
        if (delegateAdapter != null) {
            delegateAdapter.onBindViewHolder(holder, data, position);
        } else {
            Log.d("DiffAdapter", "empty adapter, possibly AdsItems haven't loaded yet");
        }
    }

    public void setData(@NonNull List<Object> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class Builder {

        private int count;
        private SparseArray<IDelegateAdapter> typeToAdapterMap;

        public Builder() {
            typeToAdapterMap = new SparseArray<>();
        }

        public Builder addAll(@NonNull List<IDelegateAdapter> adapters) {
            for(int i = 0; i < adapters.size(); i++) {
                typeToAdapterMap.put(i, adapters.get(i));
            }
            count = adapters.size();
            return this;
        }

        public Builder add(@NonNull IDelegateAdapter delegateAdapter) {
            typeToAdapterMap.put(count++, delegateAdapter);
            return this;
        }

        public CompositeDelegateAdapter build() {
            if (count == 0) throw new IllegalArgumentException("register at least one adapter");
            return new CompositeDelegateAdapter(typeToAdapterMap);
        }
    }
}
