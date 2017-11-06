package com.example.delegateadapter.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dumchev on 03.11.17.
 */
public class CompositeDelegateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = CompositeDelegateAdapter.class.getSimpleName();
    private static final int FIRST_VIEW_TYPE = 0;

    protected final SparseArray<IDelegateAdapter> typeToAdapterMap;
    private @NonNull List<? extends Object> data = new ArrayList<>();

    protected CompositeDelegateAdapter(@NonNull SparseArray<IDelegateAdapter> typeToAdapterMap) {
        this.typeToAdapterMap = typeToAdapterMap;
    }

    @Override
    public int getItemViewType(int position) {
        for (int i = FIRST_VIEW_TYPE; i < typeToAdapterMap.size(); i++) {
            final IDelegateAdapter delegate = typeToAdapterMap.valueAt(i);
            if (delegate.isForViewType(data, position)) {
                return typeToAdapterMap.keyAt(i);
            }
        }
        throw new NullPointerException("Can not get viewType for position " + position);
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return typeToAdapterMap.get(viewType).onCreateViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final IDelegateAdapter delegateAdapter = typeToAdapterMap.get(getItemViewType(position));
        if (delegateAdapter != null) {
            delegateAdapter.onBindViewHolder(holder, data, position);
        } else {
            throw new NullPointerException("can not find adapter for position " + position);
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        typeToAdapterMap.get(holder.getItemViewType()).onRecycled(holder);
    }

    public void swapData(@NonNull List<? extends Object> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public final int getItemCount() {
        return data.size();
    }

    public static class Builder {

        private int count;
        private final SparseArray<IDelegateAdapter> typeToAdapterMap;

        public Builder() {
            typeToAdapterMap = new SparseArray<>();
        }

        public Builder add(@NonNull IDelegateAdapter delegateAdapter) {
            typeToAdapterMap.put(count++, delegateAdapter);
            return this;
        }

        public CompositeDelegateAdapter build() {
            if (count == 0) throw new IllegalArgumentException("Register at least one adapter");
            return new CompositeDelegateAdapter(typeToAdapterMap);
        }
    }
}
