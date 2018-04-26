package com.example.dumchev.delegateadapters.base.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.example.delegateadapter.delegate.diff.IComparableItem;

/**
 * @author dumchev on 05.11.17.
 */

public class ImageViewModel implements IComparableItem {

    @NonNull public final String title;
    @NonNull public final @DrawableRes int imageRes;

    public ImageViewModel(@NonNull String title, @NonNull int imageRes) {
        this.title = title;
        this.imageRes = imageRes;
    }

    @Override
    public Object id() {
        return title;
    }

    @Override
    public Object content() {
        return title + imageRes;
    }
}
