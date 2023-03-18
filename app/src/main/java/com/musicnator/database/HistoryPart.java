package com.musicnator.database;

import androidx.room.ColumnInfo;

public class HistoryPart {
    @ColumnInfo(name = "history_id")
    private long historyId;

    @ColumnInfo(name = "part_id")
    private long partId;

    @ColumnInfo(name = "piece_id")
    private long pieceId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "part_num")
    private int partNum;

    @ColumnInfo(name = "is_priority")
    private boolean isPriority;

    @ColumnInfo(name = "is_active")
    private boolean isActive;

    @ColumnInfo(name = "date")
    private String date;

    public long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(long historyId) {
        this.historyId = historyId;
    }

    public long getPartId() {
        return partId;
    }

    public void setPartId(long partId) {
        this.partId = partId;
    }

    public long getPieceId() {
        return pieceId;
    }

    public void setPieceId(long pieceId) {
        this.pieceId = pieceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartNum() {
        return partNum;
    }

    public void setPartNum(int partNum) {
        this.partNum = partNum;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public void setPriority(boolean priority) {
        isPriority = priority;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
