package com.musicnator.ui.home;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.musicnator.ui.commom.PieceAdapter;
import com.musicnator.R;
import com.musicnator.database.PiecePart;

public class HomePieceAdapter extends PieceAdapter {

    public HomePieceAdapter(Context context){
        super(context);
    }

    @Override
    public void onBindViewHolder(@NonNull PieceViewHolder holder, int position) {
        PiecePart currentItem = this.getPieceAt(position);

        holder.mTextView.setText(currentItem.getName() + " " + currentItem.getPartNum());

        if (currentItem.isPriority()) {
            holder.mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.yellow));
        } else {
            holder.mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        }
    }
}
