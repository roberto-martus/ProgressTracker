package com.cleancoder.base.android.util;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.MoreObjects;

/**
 * Created by Leonid on 04.01.2015.
 */
public abstract class LoadedResourceProvider<R> implements ResourceProvider<R>, Parcelable {

    private final int resId;

    public LoadedResourceProvider(int resId) {
        this.resId = resId;
    }

    public LoadedResourceProvider(Parcel in) {
        resId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(resId);
    }

    @Override
    public abstract R get(Context context);

    @Override
    public int describeContents() {
        return 0;
    }

    protected int getResourceId() {
        return resId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LoadedResourceProvider)) {
            return false;
        }
        LoadedResourceProvider other = (LoadedResourceProvider) obj;
        return (resId == other.resId);
    }

    @Override
    public int hashCode() {
        return resId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("resId", resId)
                .toString();
    }

}
