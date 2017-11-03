package com.example.delegateadapter.delegate;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author dumchev on 03.11.17.
 */

public abstract class DelegateAdapter implements IDelegateAdapter {

    protected View inflate(@LayoutRes int id, @NonNull ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(id, parent, false);
    }
}

