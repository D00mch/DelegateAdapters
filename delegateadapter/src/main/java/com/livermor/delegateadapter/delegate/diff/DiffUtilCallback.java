package com.livermor.delegateadapter.delegate.diff;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/**
 * @author dumchev on 16.11.17.
 */
public class DiffUtilCallback extends DiffUtil.Callback {

    private final List<? extends DiffUtilItem> oldList;
    private final List<? extends DiffUtilItem> newList;

    public DiffUtilCallback(@NonNull List<? extends DiffUtilItem> oldList,
                     @NonNull List<? extends DiffUtilItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).id().equals(newList.get(newItemPosition).id());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).content().equals(newList.get(newItemPosition).content());
    }
}

