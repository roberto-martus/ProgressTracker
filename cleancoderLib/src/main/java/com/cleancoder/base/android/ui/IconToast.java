package com.cleancoder.base.android.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cleancoder.base.R;
import com.cleancoder.base.android.util.DrawableFromResourcesProvider;
import com.cleancoder.base.android.util.ResourceProvider;
import com.cleancoder.base.android.util.StringFromResourcesProvider;
import com.cleancoder.base.android.util.WrappedResourceProvider;

/**
 * Created by Leonid on 04.01.2015.
 */
public class IconToast {

    public static enum IconPosition {
        LEFT(R.layout.icon_toast_icon_at_left),
        RIGHT(R.layout.icon_toast_icon_at_right);

        private final int layoutId;

        private IconPosition(int layoutId) {
            this.layoutId = layoutId;
        }

        public int getLayoutId() {
            return layoutId;
        }
    }

    public static IconToast newLong() {
        return new IconToast(Toast.LENGTH_LONG);
    }

    public static IconToast newShort() {
        return new IconToast(Toast.LENGTH_SHORT);
    }

    private static final WrappedResourceProvider<String>
            EMPTY_TEXT_PROVIDER = new WrappedResourceProvider<String>("");

    private static final WrappedResourceProvider<Drawable>
            EMPTY_ICON_PROVIDER = new WrappedResourceProvider<Drawable>((Drawable) null);

    private final int duration;
    private ResourceProvider<Drawable> iconProvider;
    private ResourceProvider<String> textProvider;
    private IconPosition iconPosition;

    private IconToast(int duration) {
        this.duration = duration;
        this.textProvider = EMPTY_TEXT_PROVIDER;
        this.iconProvider = EMPTY_ICON_PROVIDER;
        this.iconPosition = IconPosition.LEFT;
    }

    public IconToast setText(String text) {
        textProvider = (text != null) ? new WrappedResourceProvider<String>(text) : EMPTY_TEXT_PROVIDER;
        return this;
    }

    public IconToast setText(int resId) {
        textProvider = new StringFromResourcesProvider(resId);
        return this;
    }

    public IconToast setIcon(int resId) {
        iconProvider = new DrawableFromResourcesProvider(resId);
        return this;
    }

    public IconToast setIcon(Drawable icon) {
        iconProvider = (icon != null) ? new WrappedResourceProvider<Drawable>(icon) : EMPTY_ICON_PROVIDER;
        return this;
    }

    public IconToast setIconPosition(IconPosition iconPosition) {
        this.iconPosition = iconPosition;
        return this;
    }

    public Toast show(Context context) {
        Toast toast = new Toast(context.getApplicationContext());
        toast.setDuration(duration);
        View view = prepareView(context);
        toast.setView(view);
        toast.show();
        view.requestFocus();
        return toast;
    }

    private View prepareView(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(iconPosition.getLayoutId(), null);
        ImageView iconView = (ImageView) view.findViewById(R.id.toast_icon);
        TextView textView = (TextView) view.findViewById(R.id.toast_text);
        iconView.setImageDrawable(iconProvider.get(context));
        textView.setText(textProvider.get(context));
        return view;
    }

}
