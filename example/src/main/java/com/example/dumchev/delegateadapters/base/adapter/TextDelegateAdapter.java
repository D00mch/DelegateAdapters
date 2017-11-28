package com.example.dumchev.delegateadapters.base.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.delegateadapter.delegate.BaseDelegateAdapter;
import com.example.delegateadapter.delegate.BaseViewHolder;
import com.example.dumchev.delegateadapters.R;
import com.example.dumchev.delegateadapters.base.model.TextViewModel;

import java.util.List;

/**
 * @author dumchev on 03.11.17.
 */
public class TextDelegateAdapter extends
    BaseDelegateAdapter<TextDelegateAdapter.TextViewHolder, TextViewModel> {

    @Override
    protected void onBindViewHolder(@NonNull View view,
                                    @NonNull TextViewModel item,
                                    @NonNull TextViewHolder viewHolder) {
        viewHolder.tvTitle.setText(item.title);
        viewHolder.tvDescription.setText(item.description);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.text_item;
    }

    @NonNull
    @Override
    protected TextViewHolder createViewHolder(View parent) {
        return new TextViewHolder(parent);
    }

    @Override
    public boolean isForViewType(@NonNull List<?> items, int position) {
        return items.get(position) instanceof TextViewModel;
    }

    final static class TextViewHolder extends BaseViewHolder {

        private TextView tvTitle;
        private TextView tvDescription;

        private TextViewHolder(View parent) {
            super(parent);
            tvTitle = parent.findViewById(R.id.tv_title);
            tvDescription = parent.findViewById(R.id.tv_description);
        }
    }
}
