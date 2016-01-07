package com.tuannt.testswitch.ui;

import android.content.Context;
import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

/**
 * Comment
 * TuanNT
 */
@EFragment
public abstract class BaseFragment extends Fragment implements OnBaseActivityListener {
    private OnBaseActivityListener mActivityController;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBaseActivityListener) {
            mActivityController = (OnBaseActivityListener) context;
        } else {
            throw new ClassCastException("Please implement onBaseActivityListener");
        }
    }

    @AfterViews
    @Override
    public void init() {
        initHeaderTitle();
        initViews();
    }

    protected abstract void initViews();
    public abstract void initHeaderTitle();
    public abstract boolean canBack();

    @Override
    public void setHeaderTitle(String title) {
        getController().setHeaderTitle(title);
    }

    @Override
    public void onShowMessage(String title, String message) {
        getController().onShowMessage(title, message);
    }

    @Override
    public void onShowConfirmMessage(String title, String message, OnConfirmDialogListener listener) {
        getController().onShowConfirmMessage(title, message, listener);
    }

    public OnBaseActivityListener getController() {
        return mActivityController;
    }

}
