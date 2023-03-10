package com.musicnator;

import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import com.musicnator.database.PiecePart;

public class SessionPieceAdapter extends PieceAdapter {

    public SessionPieceAdapter(){}

    @Override
    public void onBindViewHolder(@NonNull PieceViewHolder holder, int position) {
        PiecePart currentItem = this.getPieceAt(position);

        holder.mTextView.setText(currentItem.getName() + " " + currentItem.getPartNum());

        if (currentItem.isDoneToday()) {
            holder.mTextView.setPaintFlags(holder.mTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.mTextView.setPaintFlags(holder.mTextView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }

        if (currentItem.isPriority()) {
            holder.mTextView.setTextColor(Color.parseColor("#ffff33"));
        } else {
            holder.mTextView.setTextColor(Color.parseColor("#ffffff"));
        }
    }
}
