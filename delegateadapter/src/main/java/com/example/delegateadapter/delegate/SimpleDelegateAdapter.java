package com.example.delegateadapter.delegate;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * @author dumchev on 04.11.17.
 */

abstract public class SimpleDelegateAdapter<T>
    extends BaseDelegateAdapter<BaseViewHolder, T> {

    abstract protected void inflated(@NonNull View view, @NonNull T item);

    @Override
    protected void onInflated(@NonNull View view, @NonNull T item,
                              @NonNull BaseViewHolder viewHolder) {
        inflated(view, item);
    }

    protected BaseViewHolder createViewHolder(View parent) {
        return new BaseViewHolder(parent);
    }
}
