package com.example.delegateadapter.delegate.diff;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * @author dumchev on 16.11.17.
 */
public class DiffUtilCallback extends DiffUtil.Callback {

    private final List<? extends IComparableItem> oldList;
    private final List<? extends IComparableItem> newList;

    public DiffUtilCallback(@NonNull List<? extends IComparableItem> oldList,
                     @NonNull List<? extends IComparableItem> newList) {
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

