package com.tuannt.testswitch.ui.contact;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tuannt.testswitch.R;
import com.tuannt.testswitch.models.Contact;
import com.tuannt.testswitch.ui.BaseFragment;
import com.tuannt.testswitch.ui.tag.TagListActivity_;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Contact fragment
 *
 * @author TuanNT
 */
@EFragment(R.layout.fragment_contact)
public class ContactListFragment extends BaseFragment implements ContactListAdapter.OnContactListener {
    public static final int REQUEST_ADD_TAG = 100;

    private List<Contact> mContacts = new ArrayList<>();
    private ContactListAdapter mAdapter;

    @ViewById
    RecyclerView mRecyclerView;

    @Override
    protected void initViews() {
        initRecycleView();
    }

    private void initRecycleView() {
        mContacts = Contact.getContacts();

        //TODO face data
        if (mContacts == null || mContacts.isEmpty()) {
            for (int i = 0; i < 20; i++) {
                Contact contact = Contact.builder()
                        .firstName("first Name " + i)
                        .firstName("last name ")
                        .phoneNumber("123" + i)
                        .build();
                contact.save();
                mContacts.add(contact);
            }
        }

        mAdapter = new ContactListAdapter(getContext(), mContacts, this, mRecyclerView.getWidth());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initHeaderTitle() {
    }

    @Override
    public void onContactItemClick(int position) {

    }

    @Override
    public void onAddTagClick(int position) {
        TagListActivity_.intent(ContactListFragment.this).startForResult(REQUEST_ADD_TAG);
    }

    @OnActivityResult(REQUEST_ADD_TAG)
    void onAddTagResult(int resultCode, Intent data) {
    }
}
