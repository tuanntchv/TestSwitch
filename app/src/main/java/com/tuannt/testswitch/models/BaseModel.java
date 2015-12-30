package com.tuannt.testswitch.models;

import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Comment
 * TuanNT
 */
public abstract class BaseModel extends SugarRecord implements Parcelable{
    @Override
    public int describeContents() {
        return 0;
    }
}
