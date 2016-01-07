package com.tuannt.testswitch.ui.tag;

import android.content.Intent;

import com.tuannt.testswitch.R;
import com.tuannt.testswitch.models.Tag;
import com.tuannt.testswitch.ui.BaseActivity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

/**
 * Tag List activity
 *
 * @author TuanNT
 */
@EActivity(R.layout.activity_tag_list)
public class TagListActivity extends BaseActivity implements TagListFragment.OnSelectedTagListener {
    public static final String ARG_SELECTED_TAG = "arg_selected_tag";

    @Extra
    TagListFragment.TagListAction mAction;

    @Override
    public void initViews() {
        TagListFragment fragment = (TagListFragment) getSupportFragmentManager().findFragmentById(R.id.mTagListFragment);
        fragment.setAction(mAction);
    }

    @Override
    public void initHeaderTitle() {

    }

    @Override
    public void onSelected(Tag tag) {
        Intent intent = getIntent();
        intent.putExtra(ARG_SELECTED_TAG, tag);
        setResult(RESULT_OK, intent);
        finish();
    }
}
