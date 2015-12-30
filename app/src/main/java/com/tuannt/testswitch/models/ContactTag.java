package com.tuannt.testswitch.models;

import android.os.Parcel;
import android.support.annotation.Nullable;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Comment
 * TuanNT
 */
@Data
@Builder
@Table(name = "tbl_contact_tag")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(suppressConstructorProperties = true)
public class ContactTag extends BaseModel {
    public static final Creator<ContactTag> CREATOR = new Creator<ContactTag>() {
        public ContactTag createFromParcel(Parcel source) {
            return new ContactTag(source);
        }

        public ContactTag[] newArray(int size) {
            return new ContactTag[size];
        }
    };

    @Column(name = "contact_id", notNull = true)
    private long contactId;
    @Column(name = "tag_id", notNull = true)
    private long tagId;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.contactId);
        dest.writeLong(this.tagId);
    }

    protected ContactTag(Parcel in) {
        this.contactId = in.readLong();
        this.tagId = in.readLong();
    }

    @Nullable
    public static List<Tag> getTagsByContactId(int id) {
        List<ContactTag> contactTags = Tag.find(ContactTag.class, "contact_id = ?", String.valueOf(id));
        if (contactTags == null || contactTags.isEmpty()) {
            return null;
        }
        List<Tag> tags = new ArrayList<>();
        for (ContactTag contactTag : contactTags) {
            tags.add(Tag.findById(Tag.class, contactTag.getTagId()));
        }
        return tags;
    }

    @Nullable
    public static List<Contact> getContactsByTagId(int id) {
        List<ContactTag> contactTags = Tag.find(ContactTag.class, "tag_id = ?", String.valueOf(id));
        if (contactTags == null || contactTags.isEmpty()) {
            return null;
        }
        List<Contact> tags = new ArrayList<>();
        for (ContactTag contactTag : contactTags) {
            tags.add(Contact.findById(Contact.class, contactTag.getContactId()));
        }
        return tags;
    }

    public static void deleteByContact(int contactId){
        ContactTag.deleteAll(ContactTag.class, "contact_id = ?", String.valueOf(contactId));
    }

    public static void deleteByTag(int tagId){
        ContactTag.deleteAll(ContactTag.class, "tag_id = ?", String.valueOf(tagId));
    }

    public boolean isExist() {
        String[] clause = {String.valueOf(contactId), String.valueOf(tagId)};
        return ContactTag.count(ContactTag.class, "contact_id = ? and tag_id = ?", clause) > 0;
    }

    @Override
    public long save() {
        if (isExist()) {
            return 0;
        }
        return super.save();
    }
}
