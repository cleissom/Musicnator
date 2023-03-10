package com.musicnator;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.musicnator.database.PiecePart;

import java.util.ArrayList;
import java.util.List;

public abstract class PieceAdapter extends RecyclerView.Adapter<PieceAdapter.PieceViewHolder> {

    private final List<PiecePart> mItemList;

    public PieceAdapter() {
        mItemList = new ArrayList<>();
    }

    public void updatePieces(List<PiecePart> itemList) {
        final PiecePartComparator comp = new PiecePartComparator(this.mItemList, itemList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(comp, true);
        mItemList.clear();
        mItemList.addAll(itemList);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public PieceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new PieceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PieceViewHolder holder, int position) {
        PiecePart currentItem = mItemList.get(position);

//        holder.mTextView.setText(currentItem.getName() + " " + currentItem.getPartNum());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public PiecePart getPieceAt(int position) {
        return mItemList.get(position);
    }

    public static class PieceViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public PieceViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.list_item_text);
        }

    }

    public static class PiecePartComparator extends DiffUtil.Callback {
        private final List<PiecePart> mOldList;
        private final List<PiecePart> mNewList;

        PiecePartComparator(List<PiecePart> oldList, List<PiecePart> newList){
            this.mOldList = oldList;
            this.mNewList = newList;
    }

        @Override
        public int getOldListSize() {
            return mOldList.size();
        }

        @Override
        public int getNewListSize() {
            return mNewList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldList.get(oldItemPosition).getPartId() == mNewList.get(newItemPosition).getPartId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldList.get(oldItemPosition).equalsWithoutOrderNum(mNewList.get(newItemPosition));
        }
    }
}

