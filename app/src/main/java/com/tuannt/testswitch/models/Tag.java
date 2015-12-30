package com.tuannt.testswitch.models;

import android.os.Parcel;
import android.support.annotation.Nullable;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Tag model
 * TuanNT
 */
@Data
@Builder
@Table(name = "tbl_tag")
@AllArgsConstructor(suppressConstructorProperties = true)
@EqualsAndHashCode(callSuper = false)
public class Tag extends BaseModel {
    public static final Creator<Tag> CREATOR = new Creator<Tag>() {
        public Tag createFromParcel(Parcel source) {
            return new Tag(source);
        }

        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };

    @Column(name = "tag_name", unique = true, notNull = true)
    private String tagName;
    @Column(name = "icon_path")
    private String iconPath;
    private int rank;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tagName);
        dest.writeString(this.iconPath);
        dest.writeInt(this.rank);
    }

    public Tag() {
    }

    protected Tag(Parcel in) {
        this.tagName = in.readString();
        this.iconPath = in.readString();
        this.rank = in.readInt();
    }

    public static List<Tag> getTags() {
        return Tag.listAll(Tag.class);
    }

    @Nullable
    public List<Contact> getContacts() {
        return ContactTag.getContactsByTagId(getId().intValue());
    }

    @Nullable
    public List<App> getApps() {
        return AppTag.getAppsByTagId(getId().intValue());
    }

    public boolean addContact(Contact contact) {
        ContactTag contactTag = ContactTag.builder()
                .contactId(contact.getId())
                .tagId(getId())
                .build();
        return contactTag.save() != 0;
    }

    public boolean addApp(App app) {
        AppTag appTag = AppTag.builder()
                .AppId(app.getId())
                .TagId(getId())
                .build();
        return appTag.save() != 0;
    }

    public boolean isExist() {
        String[] clause = {getTagName()};
        return Tag.count(Tag.class, "tag_name = ?", clause) > 0;
    }

    @Override
    public boolean delete() {
        AppTag.deleteByTag(getId().intValue());
        ContactTag.deleteByTag(getId().intValue());
        return super.delete();
    }

    @Override
    public long save() {
        if (isExist()) {
            return 0;
        }
        return super.save();
    }
}
