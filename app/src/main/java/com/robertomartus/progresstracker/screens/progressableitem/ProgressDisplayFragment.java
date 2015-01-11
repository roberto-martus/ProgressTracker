package com.robertomartus.progresstracker.screens.progressableitem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robertomartus.progresstracker.data.WorkAsAmount;
import com.robertomartus.progresstracker.data.adapter.StorageAdapter;
import com.robertomartus.progresstracker.meta.CurrentStorageAdaptersProvider;

/**
 * Created by Leonid on 29.12.2014.
 */
public class ProgressDisplayFragment extends Fragment {
    private static final String KEY_ITEM_ID = "item_id";

    private ProgressWheelAdapter progressDisplayAdapter;
    private StorageAdapter<WorkAsAmount, Long> workAsAmountStorageAdapter;
    private WorkAsAmount work;

    public static ProgressDisplayFragment newInstance(long itemId) {
        Bundle args = new Bundle();
        args.putLong(KEY_ITEM_ID, itemId);
        ProgressDisplayFragment fragment = new ProgressDisplayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        workAsAmountStorageAdapter = CurrentStorageAdaptersProvider.get().getWorkAsAmountStorageAdapter(getActivity());
        progressDisplayAdapter = new ProgressWheelAdapter(inflater);
        update();
        return progressDisplayAdapter.getProgressDisplay();
    }

    public void update() {
        long itemId = getArguments().getLong(KEY_ITEM_ID);
        work = workAsAmountStorageAdapter.loadByKey(itemId);
        progressDisplayAdapter.update(work);
    }

}

