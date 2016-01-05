package com.tuannt.testswitch.ui.contact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.tuannt.testswitch.R;
import com.tuannt.testswitch.models.Contact;
import com.tuannt.testswitch.ui.BaseAdapter;

import java.util.List;

/**
 * Contact Adapter
 *
 * @author TuanNT
 */
public class ContactListAdapter extends BaseAdapter {
    /**
     * Contact list listener
     */
    protected interface OnContactListener {
        void onContactItemClick(int position);

        void onAddTagClick(int position);
    }

    private final int NUM_ITEM_PER_ROW = 2;
    private List<Contact> mContacts;
    private OnContactListener mListener;

    private int mItemWidth;

    public ContactListAdapter(Context context, List<Contact> list, OnContactListener l, int recycleWidth) {
        super(context);
        this.mContacts = list;
        this.mListener = l;
        int paddingLeft = getResources().getDimensionPixelOffset(R.dimen.list_padding_left);
        int paddingRight = getResources().getDimensionPixelOffset(R.dimen.list_padding_right);
        this.mItemWidth = (recycleWidth - paddingLeft - paddingRight) / NUM_ITEM_PER_ROW;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContactHolder contactHolder = (ContactHolder) holder;
        Contact contact = mContacts.get(position);
        contactHolder.tvContactName.setText(contact.getFirstName());
        contactHolder.tvPhoneNumber.setText(contact.getPhoneNumber());
        //TODO load image here
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    private class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CircularImageView imgAvatar;
        private final ImageView imgAddTag;
        private final TextView tvContactName;
        private final TextView tvPhoneNumber;
        private final View mContainer;

        public ContactHolder(View itemView) {
            super(itemView);
            imgAvatar = (CircularImageView) itemView.findViewById(R.id.mImgAvatar);
            imgAddTag = (ImageView) itemView.findViewById(R.id.mImgAddTag);
            tvContactName = (TextView) itemView.findViewById(R.id.mTvContactName);
            tvPhoneNumber = (TextView) itemView.findViewById(R.id.mTvPhoneNumber);
            mContainer = itemView.findViewById(R.id.mContainer);

            itemView.getLayoutParams().width = mItemWidth;

            imgAddTag.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener == null) {
                return;
            }
            if (v.getId() == R.id.mImgAddTag) {
                mListener.onAddTagClick(getLayoutPosition());
            } else {
                mListener.onContactItemClick(getLayoutPosition());
            }
        }
    }
}
