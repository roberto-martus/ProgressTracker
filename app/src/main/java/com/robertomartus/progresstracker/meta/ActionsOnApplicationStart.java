package com.robertomartus.progresstracker.meta;

import android.content.Context;

import com.robertomartus.progresstracker.data.WorkAsAmount;
import com.robertomartus.progresstracker.data.WorkAsAmountGreenDaoImpl;
import com.robertomartus.progresstracker.data.adapter.StorageAdapter;
import com.robertomartus.progresstracker.screens.progressableitem.ProgressableItemActivity;

/**
 * Created by Leonid on 11.01.2015.
 */
public class ActionsOnApplicationStart {

    public static void run(Context applicationContext) {
        prepareDebugWorkAsAmountItem(applicationContext);
    }

    private static void prepareDebugWorkAsAmountItem(Context applicationContext) {
        StorageAdapter<WorkAsAmount, Long> adapter =
                CurrentStorageAdaptersProvider.get().getWorkAsAmountStorageAdapter(applicationContext);
        if (adapter.loadByKey(ProgressableItemActivity.DEBUG_ITEM_ID) == null) {
            WorkAsAmount workAsAmount =
                    new WorkAsAmountGreenDaoImpl(ProgressableItemActivity.DEBUG_ITEM_ID, 10L, 100L);
            adapter.insertOrReplace(workAsAmount);
        }
    }

}
