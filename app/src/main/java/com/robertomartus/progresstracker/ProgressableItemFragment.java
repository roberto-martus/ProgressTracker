package com.robertomartus.progresstracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Leonid on 15.12.2014.
 */
public class ProgressableItemFragment extends android.support.v4.app.Fragment {

    public static interface Callbacks {
        void onAddButtonClicked();
        void onRemoveButtonClicked();
    }

    public static class DefaultCallbacks implements Callbacks {

        @Override
        public void onAddButtonClicked() {
            // override to implement
        }

        @Override
        public void onRemoveButtonClicked() {
            // override to implement
        }
    }

    private static final Callbacks DUMMY_CALLBACKS = new DefaultCallbacks();

    private Callbacks callbacks;
    private View contentView;
    private View addButton;
    private View removeButton;

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
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });
        removeButton = contentView.findViewById(R.id.remove_button);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRemoveButtonClicked();
            }
        });
    }

    private void onAddButtonClicked() {
        addButton.setEnabled(false);
        callbacks.onAddButtonClicked();
        addButton.setEnabled(true);
    }

    private void onRemoveButtonClicked() {
        removeButton.setEnabled(false);
        callbacks.onRemoveButtonClicked();
        removeButton.setEnabled(true);
    }

}

