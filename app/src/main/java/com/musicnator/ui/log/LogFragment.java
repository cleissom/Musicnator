package com.musicnator.ui.log;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.musicnator.R;
import com.musicnator.database.HistoryPart;
import com.musicnator.ui.commom.PieceViewModel;
import com.musicnator.ui.session.SessionPieceAdapter;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogFragment extends Fragment {

    private PieceViewModel mPieceViewModel;
    private RecyclerView mRecyclerView;
    private LogHistoryAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mPieceViewModel = new ViewModelProvider(requireActivity()).get(PieceViewModel.class);
        return inflater.inflate(R.layout.fragment_log, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler_view_log);

        mAdapter = new LogHistoryAdapter(requireActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        mPieceViewModel.getAllHistory().observe(requireActivity(), historyParts -> {
            Map<String, List<HistoryPart>> historyGrouped = historyParts.stream().collect(Collectors.groupingBy(HistoryPart::getDate));
            List<String> dates = historyGrouped.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            mAdapter.updateData(dates, historyGrouped);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}