package com.robertomartus.progresstracker.screens.progressableitem;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleancoder.base.android.util.LockingOnViewClickListener;
import com.cleancoder.base.android.util.Unlocker;
import com.robertomartus.progresstracker.R;

/**
 * Created by Leonid on 15.12.2014.
 */
public class ProgressableItemFragment extends android.support.v4.app.Fragment {

    public static interface Callbacks {
        void onAddButtonClicked(Unlocker addButtonUnlocker);
        void onRemoveButtonClicked(Unlocker removeButtonUnlocker);
    }

    public static class DefaultCallbacks implements Callbacks {

        @Override
        public void onAddButtonClicked(Unlocker addButtonUnlocker) {
            // override to implement
        }

        @Override
        public void onRemoveButtonClicked(Unlocker removeButtonUnlocker) {
            // override to implement
        }
    }

    private static final Callbacks DUMMY_CALLBACKS = new DefaultCallbacks();

    private Callbacks callbacks;
    private View contentView;
    private View addButton;
    private View removeButton;


    public static ProgressableItemFragment newInstance(long itemId) {
        // TODO
        return new ProgressableItemFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callbacks = (Callbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement " + Callbacks.class.getName());
        }
    }

    @Override
    public void onDetach() {
        callbacks = DUMMY_CALLBACKS;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_progressable_item, null);
        initContentView();
        return contentView;
    }

    private void initContentView() {
        addButton = contentView.findViewById(R.id.add_button);
        addButton.setOnClickListener(new LockingOnViewClickListener() {
            @Override
            protected void onClickAfterViewLocked(View view) {
                callbacks.onAddButtonClicked(this);
            }
        });
        removeButton = contentView.findViewById(R.id.remove_button);
        removeButton.setOnClickListener(new LockingOnViewClickListener() {
            @Override
            protected void onClickAfterViewLocked(View view) {
                callbacks.onRemoveButtonClicked(this);
            }
        });
    }

}

