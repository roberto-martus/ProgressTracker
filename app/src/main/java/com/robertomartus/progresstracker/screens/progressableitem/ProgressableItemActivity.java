package com.robertomartus.progresstracker.screens.progressableitem;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.cleancoder.base.android.ui.LockingOnViewClickListener;
import com.cleancoder.base.android.util.TaggedLogger;
import com.robertomartus.progresstracker.R;
import com.robertomartus.progresstracker.data.WorkAsAmount;
import com.robertomartus.progresstracker.data.adapter.StorageAdapter;
import com.robertomartus.progresstracker.meta.CurrentStorageAdaptersProvider;


public class ProgressableItemActivity extends ActionBarActivity
        implements AddProgressDialogFragment.Callbacks {
    private static final TaggedLogger logger = TaggedLogger.forClass(ProgressableItemActivity.class);

    private static final String TAG_PROGRESS_DISPLAY_FRAGMENT = "progress_display";
    private static final String TAG_ADD_PROGRESS_DIALOG_FRAGMENT = "add_progress_dialog";

    public static final long DEBUG_ITEM_ID = 1342;

    private static final int MIN_PROGRESS_TO_ADD = 1;

    private StorageAdapter<WorkAsAmount, Long> workAsAmountStorageAdapter;
    private WorkAsAmount work;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressable_item);
        setOnCreate(savedInstanceState);
    }

    private void setOnCreate(Bundle savedInstanceState) {
        workAsAmountStorageAdapter =
                CurrentStorageAdaptersProvider.get().getWorkAsAmountStorageAdapter(this);
        work = workAsAmountStorageAdapter.loadByKey(DEBUG_ITEM_ID);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.progress_display_fragment_container,
                         ProgressDisplayFragment.newInstance(DEBUG_ITEM_ID),
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
        int maxProgress = (int) (work.getTotal() - work.getDone());
        AddProgressDialogFragment dialog = AddProgressDialogFragment.newInstance(MIN_PROGRESS_TO_ADD, maxProgress);
        dialog.show(getSupportFragmentManager(), TAG_ADD_PROGRESS_DIALOG_FRAGMENT);
    }

    private void onRemoveButtonClicked() {
        // TODO
    }

    @Override
    public void onProgressAdded(int progress) {
        addProgress(progress);
        displayCurrentProgress();
    }

    private void addProgress(int progress) {
        work.setDone(work.getDone() + progress);
        workAsAmountStorageAdapter.update(work);
    }

    private void displayCurrentProgress() {
        ProgressDisplayFragment progressDisplayFragment = (ProgressDisplayFragment)
                getSupportFragmentManager().findFragmentByTag(TAG_PROGRESS_DISPLAY_FRAGMENT);
        progressDisplayFragment.update();
    }

}
