package com.tuannt.testswitch.ui;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;

/**
 * Base Adapter
 * TuanNT
 */
public abstract class BaseAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public BaseAdapter(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public Resources getResources() {
        return mContext.getResources();
    }
}
