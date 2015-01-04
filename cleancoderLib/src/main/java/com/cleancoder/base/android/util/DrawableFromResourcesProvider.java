package com.cleancoder.base.android.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Leonid on 04.01.2015.
 */
public class DrawableFromResourcesProvider extends LoadedResourceProvider<Drawable> implements Parcelable {

    public static final Creator<DrawableFromResourcesProvider> CREATOR = new Creator<DrawableFromResourcesProvider>() {
        @Override
        public DrawableFromResourcesProvider createFromParcel(Parcel source) {
            return new DrawableFromResourcesProvider(source);
        }

        @Override
        public DrawableFromResourcesProvider[] newArray(int size) {
            return new DrawableFromResourcesProvider[size];
        }
    };

    public DrawableFromResourcesProvider(int resId) {
        super(resId);
    }

    public DrawableFromResourcesProvider(Parcel in) {
        super(in);
    }

    @Override
    public Drawable get(Context context) {
        return context.getResources().getDrawable(getResourceId());
    }

}
