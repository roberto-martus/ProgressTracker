package com.robertomartus.progresstracker.screens.progressableitem;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.cleancoder.base.android.ui.LockingOnViewClickListener;
import com.robertomartus.progresstracker.R;
import com.robertomartus.progresstracker.data.WorkAsAmount;
import com.robertomartus.progresstracker.data.adapter.StorageAdapter;
import com.robertomartus.progresstracker.meta.CurrentStorageAdaptersProvider;


public class ProgressableItemActivity extends ActionBarActivity
        implements AddProgressDialogFragment.Callbacks {

    private static final String TAG_PROGRESS_DISPLAY_FRAGMENT = "progress_display";
    private static final String TAG_ADD_PROGRESS_DIALOG_FRAGMENT = "add_progress_dialog";
    private static final long ITEM_ID = 1342;

    private StorageAdapter<WorkAsAmount, Long> workAsAmountStorageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressable_item);
        workAsAmountStorageAdapter =
                CurrentStorageAdaptersProvider.get().getWorkAsAmountStorageAdapter(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.progress_display_fragment_container,
                            ProgressDisplayFragment.newInstance(ITEM_ID),
                            TAG_PROGRESS_DISPLAY_FRAGMENT)
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
        AddProgressDialogFragment dialog = AddProgressDialogFragment.newInstance(ITEM_ID);
        dialog.show(getSupportFragmentManager(), TAG_ADD_PROGRESS_DIALOG_FRAGMENT);
    }

    private void onRemoveButtonClicked() {
        // TODO
    }

    @Override
    public void onAddProgress(int progress) {
        addProgress(progress);
        updateProgressDisplay();
    }

    private void addProgress(int progress) {
        WorkAsAmount work = workAsAmountStorageAdapter.loadByKey(ITEM_ID);
        work.setDone(work.getDone() + progress);
        workAsAmountStorageAdapter.update(work);
    }

    private void updateProgressDisplay() {
        ProgressDisplayFragment progressDisplayFragment = (ProgressDisplayFragment)
                getSupportFragmentManager().findFragmentByTag(TAG_PROGRESS_DISPLAY_FRAGMENT);
        if (progressDisplayFragment != null) {
            progressDisplayFragment.refresh();
        }
    }

}
