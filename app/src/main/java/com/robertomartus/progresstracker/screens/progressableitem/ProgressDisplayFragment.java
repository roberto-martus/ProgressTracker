package com.robertomartus.progresstracker.screens.progressableitem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robertomartus.progresstracker.R;
import com.robertomartus.progresstracker.data.WorkAsAmount;
import com.robertomartus.progresstracker.data.adapter.StorageAdapter;
import com.robertomartus.progresstracker.meta.CurrentStorageAdaptersProvider;
import com.todddavies.components.progressbar.ProgressWheel;

/**
 * Created by Leonid on 29.12.2014.
 */
public class ProgressDisplayFragment extends Fragment {

    private static final String KEY_ITEM_ID = "item_id";

    private ProgressWheel progressDisplayView;
    private StorageAdapter<WorkAsAmount, Long> workAsAmountStorageAdapter;
    private View contentView;
    private View loadingView;
    private WorkAsAmount workAsAmount;


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
        workAsAmountStorageAdapter =
                CurrentStorageAdaptersProvider.get().getWorkAsAmountStorageAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.progress_wheel, null);
        initContentView();
        return contentView;
    }

    private void initContentView() {
        progressDisplayView = (ProgressWheel) contentView.findViewById(R.id.progress_display_view);
        loadingView = contentView.findViewById(R.id.show_loading_view);
        showLoading();
    }

    private void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        progressDisplayView.setVisibility(View.INVISIBLE);
    }

    public void show(WorkAsAmount workAsAmount) {
        if (workAsAmount == null) {
            showLoading();
            return;
        }
        long progress = (workAsAmount.getDone() * 360) / workAsAmount.getTotal();
        progressDisplayView.setProgress((int) progress);
        loadingView.setVisibility(View.INVISIBLE);
        progressDisplayView.setVisibility(View.VISIBLE);
    }

}

