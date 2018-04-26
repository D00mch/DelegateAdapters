package com.example.dumchev.delegateadapters.base.model;

import android.support.annotation.NonNull;

import com.example.delegateadapter.delegate.diff.IComparableItem;

/**
 * @author dumchev on 03.11.17.
 */

public class TextViewModel implements IComparableItem {

    @NonNull public final String title;
    @NonNull public final String description;

    public TextViewModel(@NonNull String title, @NonNull String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public Object id() {
        return title;
    }

    @Override
    public Object content() {
        return title + description;
    }
}
