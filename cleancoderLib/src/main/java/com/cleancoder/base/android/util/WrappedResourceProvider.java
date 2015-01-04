package com.cleancoder.base.android.util;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by Leonid on 04.01.2015.
 */
public class WrappedResourceProvider<R> implements ResourceProvider<R>, Parcelable {

    public static final Creator<WrappedResourceProvider> CREATOR = new Creator<WrappedResourceProvider>() {
        @Override
        public WrappedResourceProvider createFromParcel(Parcel source) {
            return new WrappedResourceProvider(source);
        }

        @Override
        public WrappedResourceProvider[] newArray(int size) {
            return new WrappedResourceProvider[size];
        }
    };

    private final R resource;

    public WrappedResourceProvider(R resource) {
        this.resource = resource;
    }

    public WrappedResourceProvider(Parcel in) {
        Class<R> resourceClass = (Class<R>) in.readSerializable();
        resource = (R) in.readValue(resourceClass.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeSerializable(resource.getClass());
        out.writeValue(resource);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public R get(Context context) {
        return resource;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WrappedResourceProvider)) {
            return false;
        }
        WrappedResourceProvider other = (WrappedResourceProvider) obj;
        return Objects.equal(resource, other.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(resource);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("resource", resource)
                .toString();
    }

}