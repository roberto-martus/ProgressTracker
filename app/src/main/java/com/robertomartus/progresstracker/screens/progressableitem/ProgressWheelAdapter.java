package com.robertomartus.progresstracker.screens.progressableitem;

import android.view.LayoutInflater;

import com.robertomartus.progresstracker.R;
import com.robertomartus.progresstracker.data.WorkAsAmount;
import com.todddavies.components.progressbar.ProgressWheel;

/**
 * Created by Leonid on 11.01.2015.
 */
public class ProgressWheelAdapter extends ProgressDisplayAdapter<ProgressWheel> {

    public ProgressWheelAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    protected void setInitState(ProgressWheel progressDisplay) {
        progressDisplay.setProgress(0);
    }

    @Override
    public ProgressWheel inflate(LayoutInflater inflater) {
        return (ProgressWheel) inflater.inflate(R.layout.progress_wheel, null);
    }

    @Override
    public void display(ProgressWheel progressDisplay, WorkAsAmount work) {
        long progress = (work.getDone() * 360) / work.getTotal();
        progressDisplay.setProgress((int) progress);
    }
}
