package com.tuannt.testswitch.ui.app;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tuannt.testswitch.R;
import com.tuannt.testswitch.models.App;
import com.tuannt.testswitch.ui.BaseFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * App fragment
 *
 * @author TuanNT
 */
@EFragment(R.layout.fragment_app)
public class AppListFragment extends BaseFragment implements AppListAdapter.OnAppItemListener {

    private List<App> mApps = new ArrayList<>();
    private AppListAdapter mAdapter;

    @ViewById
    RecyclerView mRecyclerView;

    @Override
    protected void initViews() {
        initRecycleView();
    }

    @Override
    public void initHeaderTitle() {
    }

    private void initRecycleView() {
        mApps = App.getApps();
        // TODO: 1/5/2016 face data
        if (mApps == null || mApps.isEmpty()) {
            for (int i = 0; i < 20; i++) {
                App contact = App.builder()
                        .name("app " + i)
                        .mPackage("ds")
                        .iconPath("a")
                        .build();

                contact.save();
                mApps.add(contact);
            }
        }
        mAdapter = new AppListAdapter(getContext(), mApps, this, mRecyclerView.getWidth());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onAppItemClick(int position) {

    }

    @Override
    public void onAddTagClick(int position) {

    }
}
