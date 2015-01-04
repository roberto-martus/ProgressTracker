package com.robertomartus.progresstracker.screens.progressableitem;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.NumberPicker;

import com.cleancoder.base.android.ui.IconToast;
import com.google.common.base.Optional;
import com.robertomartus.progresstracker.R;
import com.robertomartus.progresstracker.data.WorkAsAmount;
import com.robertomartus.progresstracker.data.adapter.StorageAdapter;
import com.robertomartus.progresstracker.meta.CurrentStorageAdaptersProvider;

/**
 * Created by Leonid on 04.01.2015.
 */
public class AddProgressDialogFragment extends DialogFragment {

    public static interface Callbacks {
        void onAddProgress(int progress);
    }

    private static final Callbacks DUMMY_CALLBACKS = new Callbacks() {
        @Override
        public void onAddProgress(int progress) {
            // do nothing
        }
    };


    private static final String KEY_ITEM_ID = "item_id";

    private static final int MIN_PROGRESS_TO_ADD = 0;

    private Callbacks callbacks;
    private NumberPicker progressPicker;
    private StorageAdapter<WorkAsAmount, Long> workAsAmountStorageAdapter;
    private View addProgressButton;
    private View cancelButton;
    private View contentView;


    public static AddProgressDialogFragment newInstance(long itemId) {
        AddProgressDialogFragment dialogFragment = new AddProgressDialogFragment();
        Bundle args = new Bundle();
        args.putLong(KEY_ITEM_ID, itemId);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        callbacks = DUMMY_CALLBACKS;
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workAsAmountStorageAdapter =
                CurrentStorageAdaptersProvider.get().getWorkAsAmountStorageAdapter(getActivity());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_action_done);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle(R.string.dialog_add_progress_title);
        getDialog().requestWindowFeature(Window.FEATURE_LEFT_ICON);
        contentView = inflater.inflate(R.layout.dialog_fragment_add_progress, null);
        initContentView();
        return contentView;
    }

    private void initContentView() {
        progressPicker = (NumberPicker) contentView.findViewById(R.id.progress_picker);
        addProgressButton = contentView.findViewById(R.id.add_progress_button);
        cancelButton = contentView.findViewById(R.id.cancel_button);

        addProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddProgressButtonClicked();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void onAddProgressButtonClicked() {
        int number = progressPicker.getValue();
        Optional<String> errorDescription = checkInputProgress(number);
        if (errorDescription.isPresent()) {
            showError(errorDescription.get());
        } else {
            callbacks.onAddProgress(number);
            dismiss();
        }
    }

    private Optional<String> checkInputProgress(int number) {
        if (number >= MIN_PROGRESS_TO_ADD) {
            return Optional.absent();
        }
        return Optional.of(
            getString(R.string.error_you_cant_add_progress_less_than) + " " + MIN_PROGRESS_TO_ADD
        );
    }

    private void showError(String errorDescription) {
        progressPicker.requestFocus();
        IconToast.LENGTH_SHORT
                .setText(errorDescription)
                .setIcon(R.drawable.ic_alert_error)
                .show(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        setOnResume();
    }

    private void setOnResume() {
        long itemId = getArguments().getLong(KEY_ITEM_ID);
        WorkAsAmount work = workAsAmountStorageAdapter.loadByKey(itemId);
        progressPicker.setMinValue(MIN_PROGRESS_TO_ADD);
        int maxPossibleProgressToAdd = (int) (work.getTotal() - work.getDone());
        progressPicker.setMaxValue(maxPossibleProgressToAdd);
    }

}
