package com.robertomartus.progresstracker.meta;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.robertomartus.progresstracker.data.DaoMaster;
import com.robertomartus.progresstracker.data.DaoSession;

/**
 * Created by Leonid on 11.12.2014.
 */
public class ProgressTrackerApplication extends Application {

    public static final String DAO_SESSION_DATABASE_NAME = "progress-tracker-dao-db";

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDaoSession();
    }

    private void setupDaoSession() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DAO_SESSION_DATABASE_NAME, null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
