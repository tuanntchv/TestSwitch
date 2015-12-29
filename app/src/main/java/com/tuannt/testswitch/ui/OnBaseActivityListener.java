package com.tuannt.testswitch.ui;

/**
 * Base listener
 * TuanNT
 */
public interface OnBaseActivityListener {
    void init();

    void setHeaderTitle(String title);

    void onShowMessage(String title, String message);

    void onShowConfirmMessage(String title, String message, OnConfirmDialogListener listener);
}
