package com.example.delegateadapter.delegate;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author dumchev on 03.11.17.
 */
public abstract class BaseDelegateAdapter
    <VH extends BaseDelegateAdapter.BaseViewHolder, T> extends DelegateAdapter {

    abstract protected void onInflated(@NonNull View view, @NonNull T item, @NonNull VH viewHolder);

    @LayoutRes
    abstract protected int getLayoutId();

    @NonNull
    abstract protected VH createViewHolder(View parent);


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                                          .inflate(getLayoutId(), parent, false);
        final VH holder = createViewHolder(inflatedView);
        holder.setListener(new ItemInflateListener() {
            @Override
            public void inflated(Object viewType, View view) {
                //noinspection unchecked
                onInflated(view, (T) viewType, holder);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(
        @NonNull RecyclerView.ViewHolder holder,
        @NonNull List<Object> items,
        int position) {

        //noinspection unchecked
        ((BaseViewHolder) holder).bind(items.get(position));
    }


    public class BaseViewHolder extends RecyclerView.ViewHolder {
        private ItemInflateListener listener;

        protected BaseViewHolder(View parent) {
            super(parent);
        }

        public void setListener(ItemInflateListener listener) {
            this.listener = listener;
        }

        public void bind(Object item) {
            listener.inflated(item, itemView);
        }
    }

    private interface ItemInflateListener {
        void inflated(Object viewType, View view);
    }
}
