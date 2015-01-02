package com.robertomartus.progresstracker.data.adapter;

import android.content.Context;

import com.robertomartus.progresstracker.data.WorkAsAmount;

/**
 * Created by Leonid on 02.01.2015.
 */
public interface StorageAdaptersProvider {

    StorageAdapter<WorkAsAmount, Long> getWorkAsAmountStorageAdapter(Context context);

}
