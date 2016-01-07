package com.tuannt.testswitch.ui.views;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Comment
 *
 * @author TuanNT
 */
public class MultiListenerView extends RelativeLayout {
    private final String TAG = this.getClass().getSimpleName();

    public interface OnMultiListener {
        void onClick();

        void onLongPress();
    }

    private int mLongPressThreshold = 320;
    private OnMultiListener mListener;
    private Handler mHandler;
    private boolean mHasLongPress;

    public MultiListenerView(Context context) {
        this(context, null);
    }

    public MultiListenerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public MultiListenerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHandler = new Handler();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTracking();
                break;

            case MotionEvent.ACTION_CANCEL:
                stopTracking();
                break;

            case MotionEvent.ACTION_UP:
                stopTracking();
                if (!mHasLongPress) {
                    mListener.onClick();
                }
                mHasLongPress = false;
                break;
        }
        return super.onTouchEvent(event);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mHasLongPress = true;
            mListener.onLongPress();
        }
    };

    private void stopTracking() {
        mHandler.removeCallbacks(mRunnable);
    }

    private void startTracking() {
        mHandler.postDelayed(mRunnable, mLongPressThreshold);
    }

    public void setListener(OnMultiListener mListener) {
        this.mListener = mListener;
    }
}
