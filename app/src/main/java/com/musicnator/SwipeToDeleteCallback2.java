package com.musicnator;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.musicnator.database.PiecePart;

public class SwipeToDeleteCallback2 extends ItemTouchHelper.SimpleCallback {

    private final PieceAdapter mAdapter;
    private final PieceViewModel mPieceViewModel;

    public SwipeToDeleteCallback2(PieceAdapter adapter, ViewModelStoreOwner owner) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mAdapter = adapter;
        mPieceViewModel = new ViewModelProvider(owner).get(PieceViewModel.class);;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        final int fromPos = viewHolder.getAdapterPosition();
        final int toPos = target.getAdapterPosition();
        mPieceViewModel.setPiecePartOrderNum(mAdapter.getPieceAt(fromPos),toPos);
        mPieceViewModel.setPiecePartOrderNum(mAdapter.getPieceAt(toPos),fromPos);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        PiecePart piecePart = mAdapter.getPieceAt(position);
        if (!piecePart.isDoneToday()){
            mPieceViewModel.setPiecePartCompletedToday(piecePart);
        }

    }
}