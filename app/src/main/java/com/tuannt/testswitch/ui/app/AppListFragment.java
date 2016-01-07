package com.tuannt.testswitch.ui.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.tuannt.testswitch.R;
import com.tuannt.testswitch.models.App;
import com.tuannt.testswitch.models.AppListDataSet;
import com.tuannt.testswitch.models.Tag;
import com.tuannt.testswitch.ui.BaseFragment;
import com.tuannt.testswitch.ui.tag.TagListActivity;
import com.tuannt.testswitch.ui.tag.TagListActivity_;
import com.tuannt.testswitch.ui.tag.TagListFragment;
import com.tuannt.testswitch.ui.views.SelectionBar;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

/**
 * App fragment
 *
 * @author TuanNT
 */
@EFragment(R.layout.fragment_app)
public class AppListFragment extends BaseFragment implements AppListAdapter.OnAppItemListener,
        SelectionBar.OnSelectionBarListener {
    public static final int REQUEST_ADD_TAG = 100;

    private AppListDataSet mDataSet = new AppListDataSet();
    private AppListAdapter mAdapter;
    private int mCurrentPosition;

    @ViewById
    RecyclerView mRecyclerView;
    @ViewById
    SelectionBar mSelectionBar;

    @Override
    protected void initViews() {
        initRecycleView();
        mSelectionBar.setListener(this);
    }

    @Override
    public void initHeaderTitle() {
    }

    @Override
    public boolean canBack() {
        if (mDataSet.isMultiSelected()) {
            disableSelected();
            return false;
        }
        return true;
    }

    private void initRecycleView() {
        mDataSet.setApps(App.getApps());
        // TODO: 1/5/2016 face data
        if (mDataSet.getApps() == null || mDataSet.getApps().isEmpty()) {
            for (int i = 0; i < 20; i++) {
                App contact = App.builder()
                        .name("app " + i)
                        .mPackage("ds" + i)
                        .iconPath("a")
                        .build();
                contact.save();
                mDataSet.getApps().add(contact);
            }
        }
        mAdapter = new AppListAdapter(getContext(), mDataSet, this, mRecyclerView.getWidth());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void showMessageAddTagSuccess() {
        String message = getString(R.string.message_add_tag_success);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void disableSelected() {
        mDataSet.enableMultiSelected(false);
        mDataSet.clearSelectedContacts();
        mAdapter.notifyDataSetChanged();
        showSelectionBar(false);
    }

    private void updateSelectionBar() {
        String content = getString(R.string.number_of_selected_items, mDataSet.getNumOfSelected());
        if (mDataSet.getNumOfSelected() == 1) {
            content = getString(R.string.number_of_selected_item, mDataSet.getNumOfSelected());
        }
        if (mDataSet.getNumOfSelected() == 0) {
            content = getString(R.string.none_selected_item);
        }
        mSelectionBar.setSelectionInfo(content);
    }

    private void showSelectionBar(boolean isShow) {
        Animation animation;
        if (isShow) {
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.show_selection_bar);
        } else {
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.hide_selection_bar);
        }
        mSelectionBar.startAnimation(animation);
        mSelectionBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onAppItemClick(int position) {
        if (mDataSet.isMultiSelected()) {
            mDataSet.toggleSelectItem(position);
            mAdapter.notifyItemChanged(position);
            updateSelectionBar();
        } else {
            // TODO do somethings
        }
    }

    @Override
    public void onAddTagClick(int position) {
        mCurrentPosition = position;
        TagListActivity_.intent(AppListFragment.this)
                .mAction(TagListFragment.TagListAction.ADD_TAG_INTO_APP)
                .startForResult(REQUEST_ADD_TAG);
    }

    @Override
    public void onMultiSelectEnable(int position) {
        mDataSet.toggleSelectItem(position);
        updateSelectionBar();
        if (mDataSet.isMultiSelected()) {
            mAdapter.notifyItemChanged(position);
        } else {
            mDataSet.enableMultiSelected(true);
            mAdapter.notifyDataSetChanged();
            showSelectionBar(true);
        }
    }

    @Override
    public void onSelectionAddTagClick() {
        TagListActivity_.intent(AppListFragment.this)
                .mAction(TagListFragment.TagListAction.ADD_TAG_INTO_APP)
                .startForResult(REQUEST_ADD_TAG);
    }

    @OnActivityResult(REQUEST_ADD_TAG)
    void onAddTagResult(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            Tag tag = data.getParcelableExtra(TagListActivity.ARG_SELECTED_TAG);
            if (mDataSet.isMultiSelected()) { // add to multi contacts
                mDataSet.addTagIntoSelectedApps(tag);
                disableSelected();
            } else { // add to single contact
                mDataSet.addTagIntoSelectedContact(mCurrentPosition, tag);
            }
            showMessageAddTagSuccess();
        }
    }
}
