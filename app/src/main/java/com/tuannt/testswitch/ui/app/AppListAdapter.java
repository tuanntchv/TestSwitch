package com.tuannt.testswitch.ui.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.tuannt.testswitch.R;
import com.tuannt.testswitch.models.AppListDataSet;
import com.tuannt.testswitch.ui.BaseAdapter;
import com.tuannt.testswitch.ui.views.MultiListenerView;

/**
 * Contact Adapter
 *
 * @author TuanNT
 */
public class AppListAdapter extends BaseAdapter {
    /**
     * Contact list listener
     */
    protected interface OnAppItemListener {
        void onAppItemClick(int position);

        void onAddTagClick(int position);

        void onMultiSelectEnable(int position);
    }

    private final int NUM_ITEM_PER_ROW = 2;
    private AppListDataSet mDataSet;
    private OnAppItemListener mListener;

    private int mItemWidth;

    public AppListAdapter(Context context, AppListDataSet dataSet, OnAppItemListener l, int recycleWidth) {
        super(context);
        this.mDataSet = dataSet;
        this.mListener = l;
        int paddingLeft = getResources().getDimensionPixelOffset(R.dimen.list_padding_left);
        int paddingRight = getResources().getDimensionPixelOffset(R.dimen.list_padding_right);
        this.mItemWidth = (recycleWidth - paddingLeft - paddingRight) / NUM_ITEM_PER_ROW;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);
        return new AppHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AppHolder appHolder = (AppHolder) holder;
        appHolder.tvAppName.setText(mDataSet.getName(position));
        appHolder.mContainer.setSelected(mDataSet.isSelectedItem(position));
        showBtnAddAddTag(appHolder.imgAddTag, !mDataSet.isMultiSelected());
        //TODO load image here
    }

    private void showBtnAddAddTag(View view, boolean iShow) {
        if ((iShow && view.getVisibility() == View.INVISIBLE) ||
                (!iShow && view.getVisibility() == View.VISIBLE)) {
            Animation animation;
            if (iShow) {
                animation = AnimationUtils.loadAnimation(getContext(), R.anim.show_add_tag_button);
            } else {
                animation = AnimationUtils.loadAnimation(getContext(), R.anim.hide_add_tag_button);
            }
            view.startAnimation(animation);
        }
        view.setVisibility(iShow ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return mDataSet.getSize();
    }

    private class AppHolder extends RecyclerView.ViewHolder implements View.OnClickListener, MultiListenerView.OnMultiListener {
        private final CircularImageView imgAvatar;
        private final ImageView imgAddTag;
        private final TextView tvAppName;
        private final MultiListenerView mContainer;

        public AppHolder(View itemView) {
            super(itemView);
            imgAvatar = (CircularImageView) itemView.findViewById(R.id.mImgAvatar);
            imgAddTag = (ImageView) itemView.findViewById(R.id.mImgAddTag);
            tvAppName = (TextView) itemView.findViewById(R.id.mTvAppName);
            mContainer = (MultiListenerView) itemView.findViewById(R.id.mContainer);

            itemView.getLayoutParams().width = mItemWidth;
            mContainer.setListener(this);
            imgAddTag.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.mImgAddTag) {
                mListener.onAddTagClick(getLayoutPosition());
            } else {
                mListener.onAppItemClick(getLayoutPosition());
            }
        }

        @Override
        public void onClick() {
            mListener.onAppItemClick(getLayoutPosition());
        }

        @Override
        public void onLongPress() {
            int index = getLayoutPosition();
            if (index < 0) {
                return;
            }
            mListener.onMultiSelectEnable(getLayoutPosition());
        }
    }
}
