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
 * Comment
 * TuanNT
 */
@Data
@Builder
@Table(name = "tbl_app")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(suppressConstructorProperties = true)
public class App extends BaseModel {
    public static final Creator<App> CREATOR = new Creator<App>() {
        public App createFromParcel(Parcel source) {
            return new App(source);
        }

        public App[] newArray(int size) {
            return new App[size];
        }
    };

    @Column(name = "app_name", notNull = true)
    private String name;
    @Column(name = "m_package", notNull = true)
    private String mPackage;
    @Column(name = "icon_path", notNull = true)
    private String iconPath;
    private int rank;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.mPackage);
        dest.writeString(this.iconPath);
        dest.writeInt(this.rank);
    }

    protected App(Parcel in) {
        this.name = in.readString();
        this.mPackage = in.readString();
        this.iconPath = in.readString();
        this.rank = in.readInt();
    }

    public static List<App> getApps() {
        return App.listAll(App.class);
    }

    public boolean addTag(Tag tag) {
        AppTag appTag = AppTag.builder()
                .AppId(getId())
                .TagId(tag.getId())
                .build();
        return appTag.save() != 0;
    }

    public boolean isExist() {
        String[] clause = {String.valueOf(getMPackage())};
        return App.count(App.class, "m_package = ?", clause) > 0;
    }

    @Override
    public boolean delete() {
        AppTag.deleteByApp(getId().intValue());
        return super.delete();
    }

    @Nullable
    public List<Tag> getTags() {
        return AppTag.getTagsByAppId(getId().intValue());
    }

    @Override
    public long save() {
        if (isExist()) {
            return 0;
        }
        return super.save();
    }
}
