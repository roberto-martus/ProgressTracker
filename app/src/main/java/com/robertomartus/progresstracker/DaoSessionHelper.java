package com.robertomartus.progresstracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.robertomartus.progresstracker.data.DaoMaster;

/**
 * Created by Leonid on 11.12.2014.
 */
public class DaoSessionHelper {

    public static final class SetupResult {
        private final DaoMaster daoMaster;
        private final SQLiteDatabase database;

        private SetupResult(SQLiteDatabase database) {
            this.daoMaster = new DaoMaster(database);
            this.database = database;
        }

        public DaoMaster getDaoMaster() {
            return daoMaster;
        }

        public SQLiteDatabase getDatabase() {
            return database;
        }
    }

    public static SetupResult setupDaoSession(Context context, String databaseName) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, databaseName, null);
        return new SetupResult(helper.getWritableDatabase());
    }

}
