package com.musicnator.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.musicnator.HomeItemTouchCallback;
import com.musicnator.PieceAdapter;
import com.musicnator.PieceViewModel;
import com.musicnator.R;
import com.musicnator.SessionItemTouchCallback;
import com.musicnator.SessionPieceAdapter;
import com.musicnator.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private PieceViewModel mPieceViewModel;
    private RecyclerView mRecyclerView;
    private PieceAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mPieceViewModel = new ViewModelProvider(requireActivity()).get(PieceViewModel.class);
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler_view_session);

        mAdapter = new SessionPieceAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SessionItemTouchCallback(mAdapter, requireActivity()));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mPieceViewModel.getSessionPieceParts().observe(requireActivity(), pieceParts -> {
            mAdapter.updatePieces(pieceParts);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}