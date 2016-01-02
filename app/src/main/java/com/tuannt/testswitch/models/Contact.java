package com.tuannt.testswitch.models;

import android.os.Parcel;
import android.support.annotation.Nullable;

import com.orm.dsl.Column;
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Contact model
 * TuanNT
 */
@Builder
@Data
@Table(name = "tbl_contact")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(suppressConstructorProperties = true)
public class Contact extends BaseModel {
    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number", notNull = true)
    private String phoneNumber;
    @Column(name = "icon_path")
    private String iconPath;
    private int rank;
    @Ignore
    private boolean isSelected;

    public Contact() {
    }

    protected Contact(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.phoneNumber = in.readString();
        this.iconPath = in.readString();
        this.rank = in.readInt();
        this.isSelected = in.readByte() !=0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.iconPath);
        dest.writeInt(this.rank);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }

    public static List<Contact> getContacts() {
        return Contact.listAll(Contact.class);
    }

    @Override
    public long save() {
        if (isExist()) {
            return 0;
        }
        return super.save();
    }

    @Override
    public boolean delete() {
        ContactTag.deleteByContact(getId().intValue());
        return super.delete();
    }

    @Nullable
    public List<Tag> getTags() {
        return ContactTag.getTagsByContactId(getId().intValue());
    }

    public boolean isExist() {
        String[] clause = {phoneNumber};
        return Contact.count(Contact.class, "phone_number = ?", clause) > 0;
    }

    public boolean addTag(Tag tag) {
        ContactTag contactTag = ContactTag.builder()
                .tagId(tag.getId())
                .contactId(getId()).build();
        return contactTag.save() != 0;
    }

    public String getNameDisplay(){
        return firstName; // TODO update later
    }
}
