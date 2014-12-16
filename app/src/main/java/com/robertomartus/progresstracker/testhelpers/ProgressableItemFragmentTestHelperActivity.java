package com.robertomartus.progresstracker.testhelpers;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.robertomartus.progresstracker.ProgressableItemFragment;
import com.robertomartus.progresstracker.R;

/**
 * Created by Leonid on 15.12.2014.
 */
public class ProgressableItemFragmentTestHelperActivity extends FragmentActivity
        implements ProgressableItemFragment.Callbacks {

    private ProgressableItemFragment.Callbacks callbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_helper);
    }

    public void setFragmentCallbacks(ProgressableItemFragment.Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public void onAddButtonClicked() {
        callbacks.onAddButtonClicked();
    }

    @Override
    public void onRemoveButtonClicked() {
        callbacks.onRemoveButtonClicked();
    }

}
