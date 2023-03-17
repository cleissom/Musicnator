package com.musicnator.ui.session;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.musicnator.ui.commom.PieceAdapter;
import com.musicnator.ui.commom.PieceViewModel;
import com.musicnator.database.PiecePart;

public class SessionItemTouchCallback extends ItemTouchHelper.SimpleCallback {

    private final PieceAdapter mAdapter;
    private final PieceViewModel mPieceViewModel;

    public SessionItemTouchCallback(PieceAdapter adapter, ViewModelStoreOwner owner) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mAdapter = adapter;
        mPieceViewModel = new ViewModelProvider(owner).get(PieceViewModel.class);
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        PiecePart piecePart = mAdapter.getPieceAt(position);
        if (!piecePart.isDoneToday()){
            mPieceViewModel.setPiecePartCompletedToday(piecePart);
        } else {
            mPieceViewModel.setPiecePartNotCompletedToday(piecePart);
        }

    }
}