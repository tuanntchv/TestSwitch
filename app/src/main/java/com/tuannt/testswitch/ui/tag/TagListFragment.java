package com.tuannt.testswitch.ui.tag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tuannt.testswitch.R;
import com.tuannt.testswitch.models.Tag;
import com.tuannt.testswitch.ui.BaseFragment;
import com.tuannt.testswitch.ui.OnConfirmDialogListener;

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
public class TagListFragment extends BaseFragment implements TagListAdapter.OnTagOnClickListener {
    public enum TagListAction {
        ADD_TAG_INTO_CONTACT(0), ADD_TAG_INTO_APP(1), DEFAULT(2);

        private final int value;

        TagListAction(int i) {
            this.value = i;
        }

        public int getValue() {
            return value;
        }
    }

    public interface OnSelectedTagListener {
        void onSelected(Tag tag);
    }

    private List<Tag> mTags = new ArrayList<>();
    private TagListAdapter mAdapter;
    private TagListAction mAction;

    @ViewById
    RecyclerView mRecyclerView;

    @Override
    protected void initViews() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mTags = Tag.getTags();
        //TODO face data
        if (mTags == null || mTags.isEmpty()) {
            for (int i = 0; i < 20; i++) {
                Tag tag;
                if (i == 0) {
                    tag = Tag.builder().tagName("Aag" + i).build();
                }
                if (i == 5) {
                    tag = Tag.builder().tagName("Dag" + i).build();
                } else {
                    tag = Tag.builder().tagName("Tag" + i).build();
                }
                tag.save();
                mTags.add(tag);
            }
        }

        //
        mAdapter = new TagListAdapter(getContext(), mTags, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setAction(TagListAction mAction) {
        this.mAction = mAction;
    }

    @Override
    public void initHeaderTitle() {
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    public void onDeleteClick(final int position) {
        onShowConfirmMessage(null, getString(R.string.message_confirm_delete_tag), new OnConfirmDialogListener() {
            @Override
            public void onAccept() {
                mTags.get(position).delete();
                mTags.remove(position);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancel() {
            }
        });
    }

    @Override
    public void onTagItemClick(int position) {
        if (mAction == TagListAction.ADD_TAG_INTO_APP) {
            // TODO: 1/5/2016 perform add tag into app
            if (getContext() instanceof OnSelectedTagListener) {
                ((OnSelectedTagListener) getContext()).onSelected(mTags.get(position));
            }
        } else if (mAction == TagListAction.ADD_TAG_INTO_CONTACT) {
            // TODO: 1/5/2016 perform add tag into app
            if (getContext() instanceof OnSelectedTagListener) {
                ((OnSelectedTagListener) getContext()).onSelected(mTags.get(position));
            }
        } else {
            // TODO: 1/5/2016 default preform on tag click
        }
    }
}
