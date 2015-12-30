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
@Table(name = "tbl_app_tag")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(suppressConstructorProperties = true)
public class AppTag extends BaseModel {
    public static final Creator<AppTag> CREATOR = new Creator<AppTag>() {
        public AppTag createFromParcel(Parcel source) {
            return new AppTag(source);
        }

        public AppTag[] newArray(int size) {
            return new AppTag[size];
        }
    };

    @Column(name = "app_id", notNull = true)
    private long AppId;
    @Column(name = "tag_id")
    private long TagId;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.AppId);
        dest.writeLong(this.TagId);
    }

    protected AppTag(Parcel in) {
        this.AppId = in.readLong();
        this.TagId = in.readLong();
    }

    @Nullable
    public static List<Tag> getTagsByAppId(int id) {
        List<AppTag> appTags = AppTag.find(AppTag.class, "app_id = ?", String.valueOf(id));
        if (appTags == null || appTags.isEmpty()) {
            return null;
        }
        List<Tag> tags = new ArrayList<>();
        for (AppTag appTag : appTags) {
            tags.add(Tag.findById(Tag.class, appTag.getTagId()));
        }
        return tags;
    }

    @Nullable
    public static List<App> getAppsByTagId(int id) {
        List<AppTag> appTags = AppTag.find(AppTag.class, "tag_id = ?", String.valueOf(id));
        if (appTags == null || appTags.isEmpty()) {
            return null;
        }
        List<App> tags = new ArrayList<>();
        for (AppTag appTag : appTags) {
            tags.add(App.findById(App.class, appTag.getAppId()));
        }
        return tags;
    }

    public static void deleteByApp(int appId) {
        AppTag.deleteAll(AppTag.class, "app_id = ?", String.valueOf(appId));
    }

    public static void deleteByTag(int tagId){
        AppTag.deleteAll(AppTag.class, "tag_id = ?", String.valueOf(tagId));
    }

    public boolean isExist() {
        String[] clause = {String.valueOf(getAppId()), String.valueOf(getTagId())};
        return AppTag.count(AppTag.class, "app_id = ? and tag_id = ?", clause) > 0;
    }

    @Override
    public long save() {
        if (isExist()) {
            return 0;
        }
        return super.save();
    }
}
