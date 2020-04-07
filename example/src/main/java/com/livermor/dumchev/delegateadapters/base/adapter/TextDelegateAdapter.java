package com.livermor.dumchev.delegateadapters.base.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.livermor.delegateadapter.delegate.BaseDelegateAdapter;
import com.livermor.delegateadapter.delegate.BaseViewHolder;
import com.livermor.dumchev.delegateadapters.R;
import com.livermor.dumchev.delegateadapters.base.model.TextItem;

import java.util.List;

/**
 * @author dumchev on 03.11.17.
 */
public class TextDelegateAdapter extends
    BaseDelegateAdapter<TextDelegateAdapter.TextViewHolder, TextItem> {

    @Override
    protected void onBindViewHolder(@NonNull View view,
                                    @NonNull TextItem item,
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
    public boolean isForViewType(@NonNull List<Object> items, int position) {
        return items.get(position) instanceof TextItem;
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
