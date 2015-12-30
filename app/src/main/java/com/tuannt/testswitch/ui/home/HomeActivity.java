package com.tuannt.testswitch.ui.home;

import com.tuannt.testswitch.R;
import com.tuannt.testswitch.ui.BaseActivity;

import org.androidannotations.annotations.EActivity;

/**
 * Comment
 * TuanNT
 */
@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void initViews() {
    }


    @Override
    public void initHeaderTitle() {
        //TODO update later
        setHeaderTitle("Home");
    }
}
