package com.robertomartus.progresstracker.screens.progressableitem;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.cleancoder.base.android.util.Unlocker;
import com.robertomartus.progresstracker.R;


public class ProgressableItemActivity extends ActionBarActivity implements ProgressableItemFragment.Callbacks {

    private static final long ITEM_ID = 1342;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressable_item);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, ProgressableItemFragment.newInstance(ITEM_ID))
                    .commit();
        }
    }

    @Override
    public void onAddButtonClicked(Unlocker addButtonUnlocker) {
        addButtonUnlocker.unlock();
    }

    @Override
    public void onRemoveButtonClicked(Unlocker removeButtonUnlocker) {
        removeButtonUnlocker.unlock();
    }
    
}
