package com.livermor.dumchev.delegateadapters.base.model;

import androidx.annotation.NonNull;

import com.livermor.delegateadapter.delegate.diff.DiffUtilItem;

/**
 * @author dumchev on 03.11.17.
 */
public class TextItem implements DiffUtilItem {

    @NonNull public final String title;
    @NonNull public final String description;

    public TextItem(@NonNull String title, @NonNull String description) {
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
