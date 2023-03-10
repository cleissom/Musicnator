package com.musicnator;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.musicnator.database.PieceEntity;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PieceViewHolder> {

    private List<PieceEntity> mItemList;

    public MyAdapter(List<PieceEntity> itemList) {
        mItemList = itemList;
    }

    @NonNull
    @Override
    public PieceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new PieceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PieceViewHolder holder, int position) {
        PieceEntity currentItem = mItemList.get(position);

        holder.mTextView.setText(currentItem.getName());

        if (currentItem.isActive()) {
            holder.mTextView.setPaintFlags(holder.mTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.mTextView.setPaintFlags(holder.mTextView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

//    public PieceEntity removeItem(int position) {
//        PieceEntity removedItem = mItemList.remove(position);
//        notifyItemRemoved(position);
//        return removedItem;
//    }
//
//    public void addItem(Item item, int position) {
//        mItemList.add(position, item);
//        notifyItemInserted(position);
//    }

    public static class PieceViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public PieceViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.list_item_text);
        }
    }
}

