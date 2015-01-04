package com.robertomartus.progresstracker.meta;

import com.robertomartus.progresstracker.data.adapter.GreenDaoStorageAdaptersProvider;
import com.robertomartus.progresstracker.data.adapter.StorageAdaptersProvider;

/**
 * Created by Leonid on 04.01.2015.
 */
public class CurrentStorageAdaptersProvider {

    private static final StorageAdaptersProvider
            STORAGE_ADAPTERS_PROVIDER = new GreenDaoStorageAdaptersProvider();

    public static StorageAdaptersProvider get() {
        return STORAGE_ADAPTERS_PROVIDER;
    }

}
