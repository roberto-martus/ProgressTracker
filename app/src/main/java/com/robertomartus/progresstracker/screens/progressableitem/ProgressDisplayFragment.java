package com.robertomartus.progresstracker.screens.progressableitem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robertomartus.progresstracker.R;
import com.robertomartus.progresstracker.data.WorkAsAmount;
import com.robertomartus.progresstracker.data.WorkAsAmountGreenDaoImpl;
import com.robertomartus.progresstracker.data.adapter.GreenDaoStorageAdaptersProvider;
import com.robertomartus.progresstracker.data.adapter.StorageAdapter;
import com.robertomartus.progresstracker.data.adapter.StorageAdaptersProvider;
import com.todddavies.components.progressbar.ProgressWheel;

/**
 * Created by Leonid on 29.12.2014.
 */
public class ProgressDisplayFragment extends Fragment {

    private static final StorageAdaptersProvider STORAGE_ADAPTERS_PROVIDER = new GreenDaoStorageAdaptersProvider();

    private static final String KEY_ITEM_ID = "item_id";

    private ProgressWheel progressView;
    private View contentView;
    private WorkAsAmount workAsAmount;
    private StorageAdapter<WorkAsAmount, Long> workAsAmountStorageAdapter;


    public static ProgressDisplayFragment newInstance(long itemId) {
        Bundle args = new Bundle();
        args.putLong(KEY_ITEM_ID, itemId);
        ProgressDisplayFragment fragment = new ProgressDisplayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workAsAmountStorageAdapter = STORAGE_ADAPTERS_PROVIDER.getWorkAsAmountStorageAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.progress_wheel, null);
        initContentView();
        return contentView;
    }

    private void initContentView() {
        progressView = (ProgressWheel) contentView.findViewById(R.id.progress_view);
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    public void update() {
        long itemId = getArguments().getLong(KEY_ITEM_ID);
        workAsAmountStorageAdapter.insertOrReplace(dummyWorkAsAmount(itemId));
        workAsAmount = workAsAmountStorageAdapter.loadByKey(itemId);
        displayWork();
    }

    private void displayWork() {
        long progress = (workAsAmount.getDone() * 360) / workAsAmount.getTotal();
        progressView.setProgress((int) progress);
    }

    private WorkAsAmountGreenDaoImpl dummyWorkAsAmount(long itemId) {
        return new WorkAsAmountGreenDaoImpl(itemId, 10L, 200L);
    }

}

