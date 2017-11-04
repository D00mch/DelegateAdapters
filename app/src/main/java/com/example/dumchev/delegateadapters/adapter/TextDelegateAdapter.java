package com.example.dumchev.delegateadapters.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.delegateadapter.delegate.BaseDelegateAdapter;
import com.example.delegateadapter.delegate.BaseViewHolder;
import com.example.dumchev.delegateadapters.R;
import com.example.dumchev.delegateadapters.model.TextViewModel;

import java.util.List;

/**
 * @author dumchev on 03.11.17.
 */
public class TextDelegateAdapter
    extends BaseDelegateAdapter<TextDelegateAdapter.TextViewHolder, TextViewModel> {


    @Override
    protected void onInflated(@NonNull View view,
                              @NonNull TextViewModel item,
                              @NonNull TextViewHolder viewHolder) {
        viewHolder.tvTitle.setText(item.title);
        viewHolder.tvDescription.setText(item.description);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.text_item;
    }

    @Override
    protected TextViewHolder createViewHolder(View parent) {
        return new TextViewHolder(parent);
    }

    @Override
    public boolean isForViewType(@NonNull List<Object> items, int position) {
        return items.get(position) instanceof TextViewModel;
    }


    final class TextViewHolder extends BaseViewHolder {

        private TextView tvTitle;
        private TextView tvDescription;

        private TextViewHolder(View parent) {
            super(parent);
            tvTitle = parent.findViewById(R.id.tv_title);
            tvDescription = parent.findViewById(R.id.tv_description);
        }
    }
}
