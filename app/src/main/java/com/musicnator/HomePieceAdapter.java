package com.musicnator;

import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.NonNull;

import com.musicnator.database.PiecePart;

public class HomePieceAdapter extends PieceAdapter {

    public HomePieceAdapter(){}

    @Override
    public void onBindViewHolder(@NonNull PieceViewHolder holder, int position) {
        PiecePart currentItem = this.getPieceAt(position);

        holder.mTextView.setText(currentItem.getName() + " " + currentItem.getPartNum());

        if (currentItem.isPriority()) {
            holder.mTextView.setTextColor(Color.parseColor("#ffff33"));
        } else {
            holder.mTextView.setTextColor(Color.parseColor("#ffffff"));
        }
    }
}
