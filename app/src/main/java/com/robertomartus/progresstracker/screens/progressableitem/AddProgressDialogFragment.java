package com.robertomartus.progresstracker.screens.progressableitem;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.cleancoder.base.android.ui.IconToast;
import com.cleancoder.base.android.util.DrawableFromResourcesProvider;
import com.cleancoder.base.android.util.ResourceProvider;
import com.cleancoder.base.android.util.StringFromResourcesProvider;
import com.google.common.base.Optional;
import com.robertomartus.progresstracker.R;
import com.robertomartus.progresstracker.data.WorkAsAmount;
import com.robertomartus.progresstracker.util.TitleBarDialogFragment;

import java.io.Serializable;

/**
 * Created by Leonid on 04.01.2015.
 */
public class AddProgressDialogFragment extends TitleBarDialogFragment {

    public static interface Callbacks {
        void onAddProgress(int progress);
    }

    private static final Callbacks DUMMY_CALLBACKS = new Callbacks() {
        @Override
        public void onAddProgress(int progress) {
            // do nothing
        }
    };


    private static final String KEY_WORK = "work";

    private static final int MIN_PROGRESS_TO_ADD = 0;

    private Callbacks callbacks;
    private NumberPicker progressPicker;
    private View addProgressButton;
    private View cancelButton;
    private View contentView;


    public static AddProgressDialogFragment newInstance(WorkAsAmount work) {
        AddProgressDialogFragment dialogFragment = new AddProgressDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_WORK, (Serializable) work);
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
    protected ResourceProvider<String> getTitle() {
        return new StringFromResourcesProvider(R.string.dialog_add_progress_title);
    }

    @Override
    protected ResourceProvider<Drawable> getTitleIcon() {
        return new DrawableFromResourcesProvider(R.drawable.ic_action_done);
    }

    @Override
    protected View prepareContentView(LayoutInflater inflater,
                                      @Nullable ViewGroup container,
                                      @Nullable Bundle savedInstanceState) {
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
        WorkAsAmount work = (WorkAsAmount) getArguments().getSerializable(KEY_WORK);
        progressPicker.setMinValue(MIN_PROGRESS_TO_ADD);
        int maxPossibleProgressToAdd = (int) (work.getTotal() - work.getDone());
        progressPicker.setMaxValue(maxPossibleProgressToAdd);
    }

}
