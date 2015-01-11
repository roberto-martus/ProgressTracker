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
import com.robertomartus.progresstracker.util.TitleBarDialogFragment;

/**
 * Created by Leonid on 04.01.2015.
 */
public class AddProgressDialogFragment extends TitleBarDialogFragment {

    public static interface Callbacks {
        void onProgressAdded(int progress);
    }

    private static final Callbacks DUMMY_CALLBACKS = new Callbacks() {
        @Override
        public void onProgressAdded(int progress) {
            // do nothing
        }
    };

    private static final String KEY_MIN = "min";
    private static final String KEY_MAX = "max";

    private Callbacks callbacks;
    private NumberPicker progressPicker;
    private View contentView;


    public static AddProgressDialogFragment newInstance(int min, int max) {
        AddProgressDialogFragment dialogFragment = new AddProgressDialogFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_MIN, min);
        args.putInt(KEY_MAX, max);
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
        contentView.findViewById(R.id.add_progress_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddProgressButtonClicked();
            }
        });
        contentView.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void onAddProgressButtonClicked() {
        int number = progressPicker.getValue();
        Optional<String> error = getInputError(number);
        if (error.isPresent()) {
            showError(error.get());
        } else {
            callbacks.onProgressAdded(number);
            dismiss();
        }
    }

    private Optional<String> getInputError(int number) {
        int min = getArguments().getInt(KEY_MIN);
        int max = getArguments().getInt(KEY_MAX);
        if (number >= min && number <= max) {
            return Optional.absent();
        }
        String format = getString(R.string.add_progress_dialog_error_format_you_can_add_progress_only_at_range);
        return Optional.of(String.format(format, min, max));
    }

    private void showError(String errorDescription) {
        progressPicker.requestFocus();
        IconToast.newShort()
                .setText(errorDescription)
                .setIcon(R.drawable.ic_alert_error)
                .setIconPosition(IconToast.IconPosition.RIGHT)
                .show(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        setOnResume();
    }

    private void setOnResume() {
        progressPicker.setMinValue(getArguments().getInt(KEY_MIN));
        progressPicker.setMaxValue(getArguments().getInt(KEY_MAX));
    }

}
