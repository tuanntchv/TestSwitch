package com.tuannt.testswitch.ui.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tuannt.testswitch.R;
import com.tuannt.testswitch.models.Contact;
import com.tuannt.testswitch.ui.BaseFragment;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Contact fragment
 *
 * @author TuanNT
 */
@EFragment(R.layout.fragment_contact)
public class ContactFragment extends BaseFragment implements ContactAdapter.OnContactListener {

    private List<Contact> mContacts = new ArrayList<>();
    private ContactAdapter mAdapter;

    @ViewById
    RecyclerView mRecyclerView;

    @Override
    protected void initViews() {
        initRecycleView();
    }

    private void initRecycleView() {
        for (int i = 0; i < 20; i++) {
            Contact contact = Contact.builder()
                    .firstName("first Name " + i)
                    .firstName("last name ")
                    .phoneNumber("123" + i)
                    .build();
            mContacts.add(contact);

        }
        mAdapter = new ContactAdapter(getContext(), mContacts, this, mRecyclerView.getWidth());
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

    }
}
