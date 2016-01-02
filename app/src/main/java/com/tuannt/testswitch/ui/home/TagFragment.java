package com.tuannt.testswitch.ui.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tuannt.testswitch.R;
import com.tuannt.testswitch.models.Tag;
import com.tuannt.testswitch.ui.BaseFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Comment
 *
 * @author TuanNT
 */
@EFragment(R.layout.fragment_tag)
public class TagFragment extends BaseFragment implements TagAdapter.OnTagOnClickListener {

    private List<Tag> mTags = new ArrayList<>();
    private TagAdapter mAdapter;

    @ViewById
    RecyclerView mRecyclerView;

    @Override
    protected void initViews() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        for (int i = 0; i < 20; i++) {
            Tag tag;
            if(i ==0) {
                tag = Tag.builder().tagName("Aag" + i).build();
            }     if(i == 5) {
                tag = Tag.builder().tagName("Dag" + i).build();
            }  else{
                tag = Tag.builder().tagName("Tag" + i).build();
            }
            mTags.add(tag);
        }
        mAdapter = new TagAdapter(getContext(), mTags, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initHeaderTitle() {
    }

    @Override
    public void onDeleteClick(int position) {

    }

    @Override
    public void onTagItemClick(int position) {

    }
}
