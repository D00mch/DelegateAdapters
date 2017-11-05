package com.example.dumchev.delegateadapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dumchev.delegateadapters.model.IViewModel;
import com.example.dumchev.delegateadapters.model.ImageViewModel;
import com.example.dumchev.delegateadapters.model.TextViewModel;

import java.util.List;

/**
 * Use {@link com.example.delegateadapter.delegate.CompositeDelegateAdapter}
 *
 * @author dumchev on 05.11.17.
 */
@Deprecated
public class BadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TEXT_VIEW_TYPE = 1;
    private static final int IMAGE_VIEW_TYPE = 2;

    private final List<IViewModel> items;

    public BadAdapter(List<IViewModel> items) {
        this.items = items;
    }

    public int getItemViewType(int position) {
        final IViewModel item = items.get(position);
        if (item instanceof TextViewModel) return TEXT_VIEW_TYPE;
        if (item instanceof ImageViewModel) return IMAGE_VIEW_TYPE;
        throw new IllegalArgumentException("Can't find view type for position " + position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TEXT_VIEW_TYPE) {
            holder = new TextViewHolder(inflater.inflate(R.layout.text_item, parent, false));
        } else if (viewType == IMAGE_VIEW_TYPE) {
            holder = new ImageViewHolder(inflater.inflate(R.layout.image_item, parent, false));
        } else {
            throw new IllegalArgumentException(
                "Can't create view holder from view type " + viewType);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TEXT_VIEW_TYPE) {
            TextViewHolder txtViewHolder = (TextViewHolder) holder;
            TextViewModel model = (TextViewModel) items.get(position);
            txtViewHolder.tvTitle.setText(model.title);
            txtViewHolder.tvDescription.setText(model.description);
        } else if (viewType == IMAGE_VIEW_TYPE) {
            ImageViewHolder imgViewHolder = (ImageViewHolder) holder;
            ImageViewModel model = (ImageViewModel) items.get(position);
            imgViewHolder.tvTitle.setText(model.title);
            imgViewHolder.imageView.setImageResource(model.imageRes);
        } else {
            throw new IllegalArgumentException("Can't create bind holder fro position " + position);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private static final class TextViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvDescription;

        private TextViewHolder(View parent) {
            super(parent);
            tvTitle = parent.findViewById(R.id.tv_title);
            tvDescription = parent.findViewById(R.id.tv_description);
        }
    }

    private static final class ImageViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView imageView;

        private ImageViewHolder(View parent) {
            super(parent);
            tvTitle = parent.findViewById(R.id.tv_title);
            imageView = parent.findViewById(R.id.img_bg);
        }
    }
}
