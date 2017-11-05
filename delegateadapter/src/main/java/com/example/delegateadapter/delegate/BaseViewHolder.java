package com.example.delegateadapter.delegate;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author dumchev on 04.11.17.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private ItemInflateListener listener;

    public BaseViewHolder(View parent) {
        super(parent);
    }

    public void setListener(ItemInflateListener listener) {
        this.listener = listener;
    }

    public void bind(Object item) {
        listener.inflated(item, itemView);
    }

    interface ItemInflateListener {
        void inflated(Object viewType, View view);
    }
}

