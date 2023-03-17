package com.musicnator.ui.session;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.musicnator.ui.commom.PieceAdapter;
import com.musicnator.R;
import com.musicnator.database.PiecePart;

public class SessionPieceAdapter extends PieceAdapter {

    public SessionPieceAdapter(Context context){
        super(context);
    }

    @Override
    public void onBindViewHolder(@NonNull PieceViewHolder holder, int position) {
        PiecePart currentItem = this.getPieceAt(position);

        holder.mTextView.setText(currentItem.getName() + " " + currentItem.getPartNum());
        holder.mIcon.setVisibility(View.GONE);

        if (currentItem.isDoneToday()) {
            holder.mTextView.setPaintFlags(holder.mTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.white_50));

        } else {
            holder.mTextView.setPaintFlags(holder.mTextView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            holder.mTextView.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        }
    }
}
