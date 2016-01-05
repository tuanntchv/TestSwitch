package com.tuannt.testswitch.ui.tag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuannt.testswitch.R;
import com.tuannt.testswitch.models.Tag;
import com.tuannt.testswitch.ui.BaseAdapter;

import java.util.List;

/**
 * Comment
 *
 * @author TuanNT
 */
public class TagListAdapter extends BaseAdapter {
    public interface OnTagOnClickListener {
        void onDeleteClick(int position);

        void onTagItemClick(int position);
    }

    private List<Tag> mTags;
    private OnTagOnClickListener mListener;

    public TagListAdapter(Context context, List<Tag> list, OnTagOnClickListener l) {
        super(context);
        this.mTags = list;
        this.mListener = l;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
        return new TagHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TagHolder tagHolder = (TagHolder) holder;
        Tag tag = mTags.get(position);
        tagHolder.tvTagName.setText(tag.getTagName());
    }

    @Override
    public int getItemCount() {
        return mTags.size();
    }

    private class TagHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvTagName;
        private final ImageView imgDelete;

        public TagHolder(View itemView) {
            super(itemView);
            tvTagName = (TextView) itemView.findViewById(R.id.mTvTagName);
            imgDelete = (ImageView) itemView.findViewById(R.id.mImgDelete);

            imgDelete.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener == null) {
                return;
            }
            if (v.getId() == R.id.mImgDelete) {
                mListener.onDeleteClick(getLayoutPosition());
            } else {
                mListener.onTagItemClick(getLayoutPosition());
            }
        }
    }
}
