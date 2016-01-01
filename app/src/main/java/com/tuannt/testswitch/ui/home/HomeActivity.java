package com.tuannt.testswitch.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.tuannt.testswitch.R;
import com.tuannt.testswitch.ui.BaseActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Comment
 * TuanNT
 */
@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity {
    public enum HomePager {
        CONTACT(0), TAG(1), APP(2),;

        private final int value;

        HomePager(int i) {
            value = i;
        }

        public int getValue() {
            return value;
        }
    }

    private HomePagerAdapter mAdapter;

    @ViewById
    ViewPager mViewPager;

    @Override
    public void initViews() {
        initPager();
    }

    @Override
    public void initHeaderTitle() {
    }

    private void initPager() {
        mAdapter = new HomePagerAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(mAdapter.getCount());
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int headerRes;
                if (position == HomePager.APP.getValue()) {
                    headerRes = R.string.title_app_screen;
                } else if (position == HomePager.TAG.value) {
                    headerRes = R.string.title_tag_screen;
                } else {
                    headerRes = R.string.title_contact_screen;
                }
                setHeaderTitle(getString(headerRes));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class HomePagerAdapter extends FragmentStatePagerAdapter {
        public HomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == HomePager.CONTACT.getValue()) {
                return ContactFragment_.builder().build();
            } else if (position == HomePager.TAG.getValue()) {
                return TagFragment_.builder().build();
            }
            return AppFragment_.builder().build();
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
