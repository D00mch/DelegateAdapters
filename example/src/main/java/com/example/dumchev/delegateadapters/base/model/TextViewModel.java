package com.example.dumchev.delegateadapters.base.model;

import android.support.annotation.NonNull;

/**
 * @author dumchev on 03.11.17.
 */

public class TextViewModel implements IViewModel {

    @NonNull public final String title;
    @NonNull public final String description;

    public TextViewModel(@NonNull String title, @NonNull String description) {
        this.title = title;
        this.description = description;
    }
}
