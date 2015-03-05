package com.cleancoder.base.android.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.cleancoder.base.android.util.ResourceProvider;

/**
 * Created by Leonid on 05.01.2015.
 */
public abstract class TitleBarDialogFragment extends DialogFragment {

    private ResourceProvider<String> title;
    private ResourceProvider<Drawable> titleIcon;

    protected abstract ResourceProvider<String> getTitle();
    protected abstract ResourceProvider<Drawable> getTitleIcon();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getTitle();
        titleIcon = getTitleIcon();
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (title != null) {
            getDialog().setTitle(title.get(getActivity()));
        }
        if (titleIcon != null) {
            getDialog().requestWindowFeature(Window.FEATURE_LEFT_ICON);
        }
        return prepareContentView(inflater, container, savedInstanceState);
    }

    protected abstract View prepareContentView(LayoutInflater inflater,
                                   ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (titleIcon != null) {
            getDialog().setFeatureDrawable(Window.FEATURE_LEFT_ICON, titleIcon.get(getActivity()));
        }
    }

}
