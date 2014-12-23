package com.cleancoder.base.android.util;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by Leonid on 21.12.2014.
 */
public abstract class LockingOnViewClickListener implements View.OnClickListener, Unlocker {

    private WeakReference<View> viewWeakReference;

    public LockingOnViewClickListener() {
        this.viewWeakReference = new WeakReference<>(null);
    }

    @Override
    public void onClick(View view) {
        View previouslyClickedView = viewWeakReference.get();
        if (previouslyClickedView == null) {
            viewWeakReference = new WeakReference<View>(view);
        } else {
            if (previouslyClickedView != view) {
                throw new IllegalStateException(
                        "You can't use the same locking OnClickListener for different views");
            }
        }
        view.setEnabled(false);
        onClickAfterViewLocked(view);
    }

    protected abstract void onClickAfterViewLocked(View view);

    public void unlock() {
        final View view = viewWeakReference.get();
        if (view != null) {
            view.post(new Runnable() {
                @Override
                public void run() {
                    view.setEnabled(true);
                }
            });
        }
    }
}
