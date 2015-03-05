package com.cleancoder.base.android.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.cleancoder.base.R;
import com.cleancoder.base.android.util.ResourceProvider;
import com.google.common.base.Optional;

/**
 * Created by Leonid on 04.01.2015.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NumberPickerDialogFragment extends TitleBarDialogFragment {

    public static interface Callbacks {
        void onNumberPicked(String receiver, int number);
    }

    public static class Input implements Parcelable {
        public static final Creator<Input> CREATOR = new Creator<Input>() {
            @Override
            public Input createFromParcel(Parcel source) {
                return new Input(source);
            }

            @Override
            public Input[] newArray(int size) {
                return new Input[size];
            }
        };

        private Integer min;
        private Integer max;
        private String receiver;
        private ResourceProvider<Drawable> titleIconProvider;
        private ResourceProvider<String> titleProvider;

        public Input() {
            // do nothing
        }

        public Input(Parcel in) {
            min = (Integer) in.readSerializable();
            max = (Integer) in.readSerializable();
            receiver = in.readString();
            titleIconProvider = (ResourceProvider<Drawable>)
                    in.readParcelable(ResourceProvider.class.getClassLoader());
            titleProvider = (ResourceProvider<String>)
                    in.readParcelable(ResourceProvider.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            out.writeSerializable(min);
            out.writeSerializable(max);
            out.writeString(receiver);
            out.writeParcelable((Parcelable) titleIconProvider, flags);
            out.writeParcelable((Parcelable) titleProvider, flags);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public Input min(int number) {
            this.min = number;
            return this;
        }

        public Input max(int number) {
            this.max = number;
            return this;
        }

        public Input titleIcon(ResourceProvider<Drawable> titleIconProvider) {
            this.titleIconProvider = titleIconProvider;
            return this;
        }

        public Input title(ResourceProvider<String> titleProvider) {
            this.titleProvider = titleProvider;
            return this;
        }

        public Input receiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        private int getMin() {
            return min;
        }

        private int getMax() {
            return max;
        }

        private String getReceiver() {
            return receiver;
        }

        private ResourceProvider<String> getTitleProvider() {
            return titleProvider;
        }

        private ResourceProvider<Drawable> getTitleIconProvider() {
            return titleIconProvider;
        }
    }

    private static final Callbacks DUMMY_CALLBACKS = new Callbacks() {
        @Override
        public void onNumberPicked(String receiver, int number) {
            // do nothing
        }
    };

    private static final String KEY_INPUT = "input";

    private Callbacks callbacks;
    private Input input;
    private NumberPicker numberPicker;
    private View contentView;


    public static NumberPickerDialogFragment newInstance(Input input) {
        NumberPickerDialogFragment dialogFragment = new NumberPickerDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_INPUT, input);
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
        return getInput().getTitleProvider();
    }

    @Override
    protected ResourceProvider<Drawable> getTitleIcon() {
        return getInput().getTitleIconProvider();
    }

    private Input getInput() {
        if (input == null) {
            input = getArguments().getParcelable(KEY_INPUT);
        }
        return input;
    }

    @Override
    protected View prepareContentView(LayoutInflater inflater,
                                      @Nullable ViewGroup container,
                                      @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.dialog_fragment_number_picker, null);
        initContentView();
        return contentView;
    }

    private void initContentView() {
        numberPicker = (NumberPicker) contentView.findViewById(R.id.number_picker);
        numberPicker.setMinValue(getInput().getMin());
        numberPicker.setMaxValue(getInput().getMax());
        contentView.findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOkButtonClicked();
            }
        });
        contentView.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void onOkButtonClicked() {
        int number = numberPicker.getValue();
        Optional<String> errorDescription = getInputErrorDescription(number);
        if (errorDescription.isPresent()) {
            showErrorMessage(errorDescription.get());
            return;
        }
        callbacks.onNumberPicked(getInput().getReceiver(), number);
        dismiss();
    }

    private Optional<String> getInputErrorDescription(int number) {
        if (number >= getInput().getMin() && number <= getInput().getMax()) {
            return Optional.absent();
        }
        String format = getString(R.string.number_picker_dialog_error_format_you_can_add_number_only_at_range);
        return Optional.of(String.format(format, getInput().getMin(), getInput().getMax()));
    }

    private void showErrorMessage(String errorDescription) {
        numberPicker.requestFocus();
        IconToast.lengthShort()
                .setText(errorDescription)
                .setIcon(R.drawable.ic_alert_error)
                .setIconPosition(IconToast.IconPosition.RIGHT)
                .show(getActivity());
    }

}
