package com.tuannt.testswitch.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tuannt.testswitch.R;

/**
 * Comment
 *
 * @author TuanNT
 */
public class SelectionBar extends RelativeLayout implements View.OnClickListener {
    public interface OnSelectionBarListener {
        void onSelectionAddTagClick();
    }

    private TextView mTvSelectedInfo;
    private ImageButton mImgBtnAddTag;
    private OnSelectionBarListener mListener;

    public SelectionBar(Context context) {
        this(context, null);
    }

    public SelectionBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.view_selection_bar, this, true);
        mTvSelectedInfo = (TextView) findViewById(R.id.mTvSelectedInfo);
        mImgBtnAddTag = (ImageButton) findViewById(R.id.mImgBtnAddTag);
        mImgBtnAddTag.setOnClickListener(this);
    }

    public void setSelectionInfo(String content) {
        mTvSelectedInfo.setText(content);
    }

    public void setListener(OnSelectionBarListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onClick(View v) {
        mListener.onSelectionAddTagClick();
    }
}
