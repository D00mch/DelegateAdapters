package com.example.dumchev.delegateadapters.base.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * @author dumchev on 05.11.17.
 */

public class ImageViewModel implements IViewModel {

    @NonNull public final String title;
    @NonNull public final @DrawableRes int imageRes;

    public ImageViewModel(@NonNull String title, @NonNull int imageRes) {
        this.title = title;
        this.imageRes = imageRes;
    }
}
