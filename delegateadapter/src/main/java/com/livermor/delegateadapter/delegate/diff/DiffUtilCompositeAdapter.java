package com.livermor.delegateadapter.delegate.diff;

import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.livermor.delegateadapter.delegate.CompositeDelegateAdapter;
import com.livermor.delegateadapter.delegate.DelegateAdapter;

import java.util.List;

/**
 * @author dumchev on 16.11.17.
 */
public class DiffUtilCompositeAdapter extends CompositeDelegateAdapter<DiffUtilItem> {

    protected DiffUtilCompositeAdapter(@NonNull SparseArray<DelegateAdapter> typeToAdapterMap) {
        super(typeToAdapterMap);
    }

    @Override
    public void swapData(@NonNull List<DiffUtilItem> data) {
        final DiffUtilCallback diffCallback = new DiffUtilCallback(this.data, data);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.data.clear();
        this.data.addAll(data);
        diffResult.dispatchUpdatesTo(this);
    }


    public static class Builder {

        private int count;
        private final SparseArray<DelegateAdapter> typeToAdapterMap;

        public Builder() {
            typeToAdapterMap = new SparseArray<>();
        }

        public Builder add(
            @NonNull DelegateAdapter<?, ? extends DiffUtilItem> delegateAdapter) {
            typeToAdapterMap.put(count++, delegateAdapter);
            return this;
        }

        public DiffUtilCompositeAdapter build() {
            if (count == 0) throw new IllegalArgumentException("Register at least one adapter");
            return new DiffUtilCompositeAdapter(typeToAdapterMap);
        }
    }
}
