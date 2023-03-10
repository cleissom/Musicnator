package com.musicnator.database;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import java.util.Objects;

public class PiecePart {
    @ColumnInfo(name = "part_id")
    private long partId;

    @ColumnInfo(name = "piece_id")
    private long pieceId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "part_num")
    private int partNum;

    @ColumnInfo(name = "order_num")
    private int orderNum;

    @ColumnInfo(name = "is_priority")
    private boolean isPriority;

    @ColumnInfo(name = "is_active")
    private boolean isActive;

    @ColumnInfo(name = "is_done_today")
    private boolean isDoneToday;

    public boolean equalsWithoutOrderNum(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PiecePart piecePart = (PiecePart) o;
        return partId == piecePart.partId && pieceId == piecePart.pieceId && partNum == piecePart.partNum && isPriority == piecePart.isPriority && isActive == piecePart.isActive && isDoneToday == piecePart.isDoneToday && Objects.equals(name, piecePart.name);
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

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
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

    public boolean isDoneToday() {
        return isDoneToday;
    }

    public void setDoneToday(boolean doneToday) {
        isDoneToday = doneToday;
    }
}
