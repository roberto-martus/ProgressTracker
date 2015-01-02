package com.robertomartus.progresstracker.meta;

import android.support.v4.app.Fragment;

import com.robertomartus.progresstracker.data.DaoSession;

/**
 * Created by Leonid on 27.12.2014.
 */
public class GreenDaoHelper {

    public static DaoSession getDaoSession(Fragment fragment) {
        ProgressTrackerApplication appContext =
                (ProgressTrackerApplication) fragment.getActivity().getApplicationContext();
        return appContext.getDaoSession();
    }

}
