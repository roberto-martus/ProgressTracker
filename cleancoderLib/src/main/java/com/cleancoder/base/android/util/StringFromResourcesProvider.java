package com.cleancoder.base.android.util;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Leonid on 04.01.2015.
 */
public class StringFromResourcesProvider extends LoadedResourceProvider<String> implements Parcelable {

    public static final Creator<StringFromResourcesProvider> CREATOR = new Creator<StringFromResourcesProvider>() {
        @Override
        public StringFromResourcesProvider createFromParcel(Parcel source) {
            return new StringFromResourcesProvider(source);
        }

        @Override
        public StringFromResourcesProvider[] newArray(int size) {
            return new StringFromResourcesProvider[size];
        }
    };

    public StringFromResourcesProvider(int resId) {
        super(resId);
    }

    public StringFromResourcesProvider(Parcel in) {
        super(in);
    }

    @Override
    public String get(Context context) {
        return context.getString(getResourceId());
    }

}
