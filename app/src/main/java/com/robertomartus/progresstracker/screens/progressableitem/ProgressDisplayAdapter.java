package com.robertomartus.progresstracker.screens.progressableitem;

import android.view.LayoutInflater;
import android.view.View;

import com.robertomartus.progresstracker.data.WorkAsAmount;

/**
 * Created by Leonid on 11.01.2015.
 */
public abstract class ProgressDisplayAdapter<V extends View> {
    private V progressDisplay;

    private ProgressDisplayAdapter() {
        // can not access outside
    }

    protected ProgressDisplayAdapter(LayoutInflater inflater) {
        if (inflater == null) {
            throw new AssertionError("inflater cannot be null");
        }
        progressDisplay = inflate(inflater);
        setInitState(progressDisplay);
    }

    public V getProgressDisplay() {
        return progressDisplay;
    }

    public void update(WorkAsAmount work) {
        display(progressDisplay, work);
    }

    protected abstract void setInitState(V progressDisplay);
    protected abstract V inflate(LayoutInflater inflater);
    protected abstract void display(V progressDisplay, WorkAsAmount work);
}
