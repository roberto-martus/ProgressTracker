package com.robertomartus.progresstracker.screens.progressableitem;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.cleancoder.base.android.util.LockingOnViewClickListener;
import com.robertomartus.progresstracker.R;


public class ProgressableItemActivity extends ActionBarActivity {

    private static final long ITEM_ID = 1342;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressable_item);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.progress_display_fragment_container, ProgressDisplayFragment.newInstance(ITEM_ID))
                    .commit();
        }
        initView();
    }

    private void initView() {
        findViewById(R.id.add_button).setOnClickListener(new LockingOnViewClickListener() {
            @Override
            protected void onClickAfterViewLocked(View view) {
                onAddButtonClicked();
                unlock();
            }
        });
        findViewById(R.id.remove_button).setOnClickListener(new LockingOnViewClickListener() {
            @Override
            protected void onClickAfterViewLocked(View view) {
                onRemoveButtonClicked();
                unlock();
            }
        });
    }

    private void onAddButtonClicked() {
        // TODO
    }

    private void onRemoveButtonClicked() {
        // TODO
    }
    
}
