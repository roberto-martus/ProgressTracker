package com.robertomartus.progresstracker;

import android.app.Application;

import com.robertomartus.progresstracker.data.DaoSession;

/**
 * Created by Leonid on 11.12.2014.
 */
public class ProgressTrackerApplication extends Application {

    public static final String DAO_SESSION_DATABASE_NAME = "progress-tracker-dao-db";

    private DaoSessionHelper.SetupResult setupResult;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDaoSession();
    }

    private void setupDaoSession() {
        setupResult = DaoSessionHelper.setupDaoSession(this, DAO_SESSION_DATABASE_NAME);
        daoSession = setupResult.getDaoMaster().newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public DaoSessionHelper.SetupResult getDaoSetupResult() {
        return setupResult;
    }

}
