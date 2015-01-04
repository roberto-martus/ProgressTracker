package com.cleancoder.base.android.util;

import android.content.Context;

/**
 * Created by Leonid on 04.01.2015.
 */
public interface ResourceProvider<R> {
    R get(Context context);
}
