package com.robertomartus.progresstracker.screens.progressableitem;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.cleancoder.base.android.ui.LockingOnViewClickListener;
import com.cleancoder.base.android.ui.NumberPickerDialogFragment;
import com.cleancoder.base.android.util.DrawableFromResourcesProvider;
import com.cleancoder.base.android.util.StringFromResourcesProvider;
import com.cleancoder.base.android.util.TaggedLogger;
import com.robertomartus.progresstracker.R;
import com.robertomartus.progresstracker.data.WorkAsAmount;
import com.robertomartus.progresstracker.data.adapter.StorageAdapter;
import com.robertomartus.progresstracker.meta.CurrentStorageAdaptersProvider;


public class ProgressableItemActivity extends ActionBarActivity
                                      implements NumberPickerDialogFragment.Callbacks {

    private static final TaggedLogger logger = TaggedLogger.forClass(ProgressableItemActivity.class);

    private static final String TAG_PROGRESS_DISPLAY_FRAGMENT = "progress_display";
    private static final String TAG_ADD_PROGRESS_DIALOG_FRAGMENT = "add_progress_dialog";
    private static final String TAG_REMOVE_PROGRESS_DIALOG_FRAGMENT = "remove_progress_dialog";

    public static final long DEBUG_ITEM_ID = 1342;

    private static final int MIN_PROGRESS_TO_ADD = 1;
    private static final int MIN_PROGRESS_TO_REMOVE = 1;

    private StorageAdapter<WorkAsAmount, Long> workAsAmountStorageAdapter;
    private View addButton;
    private View removeButton;
    private WorkAsAmount work;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressable_item);
        workAsAmountStorageAdapter =
                CurrentStorageAdaptersProvider.get().getWorkAsAmountStorageAdapter(this);
        work = workAsAmountStorageAdapter.loadByKey(DEBUG_ITEM_ID);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.progress_display_fragment_container,
                            ProgressDisplayFragment.newInstance(getItemId()),
                            TAG_PROGRESS_DISPLAY_FRAGMENT)
                    .commit();
        }
        initView();
    }

    private long getItemId() {
        return DEBUG_ITEM_ID;
    }

    private void initView() {
        addButton = findViewById(R.id.add_button);
        removeButton = findViewById(R.id.remove_button);
        addButton.setOnClickListener(new LockingOnViewClickListener() {
            @Override
            protected void onClickAfterViewLocked(View view) {
                onAddButtonClicked();
                unlock();
            }
        });
        removeButton.setOnClickListener(new LockingOnViewClickListener() {
            @Override
            protected void onClickAfterViewLocked(View view) {
                onRemoveButtonClicked();
                unlock();
            }
        });
        updateButtonsAddProgressAndRemoveProgress();
    }

    private void onAddButtonClicked() {
        int maxProgressUserCanAdd = (int) (work.getTotal() - work.getDone());
        NumberPickerDialogFragment.Input input = new NumberPickerDialogFragment.Input()
                .min(MIN_PROGRESS_TO_ADD)
                .max(maxProgressUserCanAdd)
                .receiver(TAG_ADD_PROGRESS_DIALOG_FRAGMENT)
                .title(new StringFromResourcesProvider(R.string.dialog_add_progress_title))
                .titleIcon(new DrawableFromResourcesProvider(R.drawable.ic_action_done));
        NumberPickerDialogFragment dialogFragment = NumberPickerDialogFragment.newInstance(input);
        dialogFragment.show(getSupportFragmentManager(), TAG_ADD_PROGRESS_DIALOG_FRAGMENT);
    }

    private void onRemoveButtonClicked() {
        int maxProgressUserCanRemove = (int) (long) work.getDone();
        NumberPickerDialogFragment.Input input = new NumberPickerDialogFragment.Input()
                .min(MIN_PROGRESS_TO_REMOVE)
                .max(maxProgressUserCanRemove)
                .receiver(TAG_REMOVE_PROGRESS_DIALOG_FRAGMENT)
                .title(new StringFromResourcesProvider(R.string.dialog_remove_progress_title))
                .titleIcon(new DrawableFromResourcesProvider(R.drawable.ic_action_cancel));
        NumberPickerDialogFragment dialogFragment = NumberPickerDialogFragment.newInstance(input);
        dialogFragment.show(getSupportFragmentManager(), TAG_REMOVE_PROGRESS_DIALOG_FRAGMENT);
    }

    @Override
    public void onNumberPicked(String receiver, int number) {
        switch (receiver) {
            case TAG_ADD_PROGRESS_DIALOG_FRAGMENT:
                addProgress(number);
                break;
            case TAG_REMOVE_PROGRESS_DIALOG_FRAGMENT:
                addProgress(-number);
                break;
            default:
                throw new AssertionError("Unknown receiver <" + receiver + ">");
        }
        ProgressDisplayFragment progressDisplayFragment = (ProgressDisplayFragment)
                getSupportFragmentManager().findFragmentByTag(TAG_PROGRESS_DISPLAY_FRAGMENT);
        progressDisplayFragment.update();
        updateButtonsAddProgressAndRemoveProgress();
    }

    private void addProgress(int progress) {
        work.setDone(work.getDone() + progress);
        workAsAmountStorageAdapter.update(work);
    }

    private void updateButtonsAddProgressAndRemoveProgress() {
        addButton.setEnabled(work.getDone() < work.getTotal());
        removeButton.setEnabled(work.getDone() > 0);
    }

}
