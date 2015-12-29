package com.tuannt.testswitch.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.tuannt.testswitch.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Base activity
 * TuanNT
 */
@EActivity
public abstract class BaseActivity extends AppCompatActivity implements OnBaseActivityListener {

    @ViewById
    public Toolbar mHeaderBar;
    @ViewById
    public TextView mTvHeaderTitle;

    @AfterViews
    @Override
    public void init() {
        initHeaderTitle();
        initViews();
    }

    public abstract void initViews();
    public abstract void initHeaderTitle();

    @Override
    public void onShowMessage(String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(true);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.show();
    }

    @Override
    public void onShowConfirmMessage(String title, String message, final OnConfirmDialogListener listener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(true);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setNegativeButton(R.string.btn_dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onAccept();
                }
            }
        });
        dialog.setPositiveButton(R.string.btn_dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onCancel();
                }
            }
        });
        dialog.show();
    }

    @Override
    public void setHeaderTitle(String title) {
        //TODO set header title here
        mTvHeaderTitle.setText(title);
    }
}
