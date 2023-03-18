package com.musicnator.ui.log;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.musicnator.R;
import com.musicnator.database.HistoryPart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogHistoryAdapter extends RecyclerView.Adapter<LogHistoryAdapter.HistoryViewHolder> {

    private final Context mContext;
    private final List<String> mDates;
    private Map<String, List<HistoryPart>> mDateToHistoryPartList;

    public LogHistoryAdapter(Context context) {
        this.mContext = context;
        this.mDates = new ArrayList<>();
        this.mDateToHistoryPartList = new HashMap<>();
    }

    public void updateData(List<String> dateList, Map<String, List<HistoryPart>> dateToHistoryPartList){
        mDates.clear();
        mDates.addAll(dateList);
        mDateToHistoryPartList = dateToHistoryPartList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_history_list_item, parent, false);
        return new LogHistoryAdapter.HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        String currentDate = mDates.get(position);
        holder.mTitle.setText(currentDate);
        List<HistoryPart> historyParts = mDateToHistoryPartList.get(currentDate);

        if (historyParts != null){
            List<String> items = historyParts.stream().map(historyPart -> historyPart.getName() + " " + historyPart.getPartNum()).collect(Collectors.toList());
            LogPartAdapter adapter = new LogPartAdapter(items);
            holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            holder.mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return mDates.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        RecyclerView mRecyclerView;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.log_history_item_title);
            mRecyclerView = itemView.findViewById(R.id.log_history_item_recyclerview);
        }
    }
}
