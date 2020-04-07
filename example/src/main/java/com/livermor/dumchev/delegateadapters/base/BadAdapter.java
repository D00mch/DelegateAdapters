package com.livermor.dumchev.delegateadapters.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.livermor.delegateadapter.delegate.diff.DiffUtilItem;
import com.livermor.dumchev.delegateadapters.R;
import com.livermor.dumchev.delegateadapters.base.model.ImageItem;
import com.livermor.dumchev.delegateadapters.base.model.TextItem;

import java.util.List;

/**
 * Use {@link com.livermor.delegateadapter.delegate.diff.DiffUtilCompositeAdapter}
 *
 * @author dumchev on 05.11.17.
 */
@Deprecated
public class BadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TEXT_VIEW_TYPE = 1;
    private static final int IMAGE_VIEW_TYPE = 2;

    private List<DiffUtilItem> items;

    public BadAdapter(List<DiffUtilItem> items) {
        this.items = items;
    }

    public int getItemViewType(int position) {
        DiffUtilItem item = items.get(position);
        if (item instanceof TextItem) return TEXT_VIEW_TYPE;
        if (item instanceof ImageItem) return IMAGE_VIEW_TYPE;
        throw new IllegalArgumentException("Can't find view type for position " + position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TEXT_VIEW_TYPE) {
            TextViewHolder txtViewHolder = (TextViewHolder) holder;
            TextItem model = (TextItem) items.get(position);
            txtViewHolder.tvTitle.setText(model.title);
            txtViewHolder.tvDescription.setText(model.description);
        } else if (viewType == IMAGE_VIEW_TYPE) {
            ImageViewHolder imgViewHolder = (ImageViewHolder) holder;
            ImageItem model = (ImageItem) items.get(position);
            imgViewHolder.tvTitle.setText(model.getTitle());
            imgViewHolder.imageView.setImageResource(model.getImageRes());
        } else {
            throw new IllegalArgumentException(
                "Can't create bind holder fro position " + position);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private static class TextViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvDescription;

        private TextViewHolder(View parent) {
            super(parent);
            tvTitle = parent.findViewById(R.id.tv_title);
            tvDescription = parent.findViewById(R.id.tv_description);
        }
    }

    private static class ImageViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView imageView;

        private ImageViewHolder(View parent) {
            super(parent);
            tvTitle = parent.findViewById(R.id.tv_title);
            imageView = parent.findViewById(R.id.img_bg);
        }
    }
}
