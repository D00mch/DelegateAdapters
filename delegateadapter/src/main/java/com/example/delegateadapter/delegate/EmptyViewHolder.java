package com.example.delegateadapter.delegate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author dumchev on 03.11.17.
 */
public class EmptyViewHolder extends RecyclerView.ViewHolder {

    public EmptyViewHolder(View itemView) {
        super(itemView);
    }

    public static EmptyViewHolder createNoAdverts(ViewGroup parent) {
        return new EmptyViewHolder(new View(parent.getContext())); // todo: test it
    }
}

