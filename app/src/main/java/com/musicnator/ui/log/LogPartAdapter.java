package com.musicnator.ui.log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.musicnator.R;

import java.util.List;

public class LogPartAdapter extends RecyclerView.Adapter<LogPartAdapter.LogPartViewHolder> {

    private final List<String> mList;

    public LogPartAdapter(List<String> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public LogPartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_part_list_item, parent, false);
        return new LogPartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LogPartViewHolder holder, int position) {
        holder.mTextView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class LogPartViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public LogPartViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTextView = itemView.findViewById(R.id.log_part_list_item_text);
        }
    }
}
