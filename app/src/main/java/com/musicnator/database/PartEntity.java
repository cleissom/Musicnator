package com.musicnator.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity(
        tableName = "parts_table",
        foreignKeys = @ForeignKey(
                entity = PieceEntity.class,
                parentColumns = "piece_id",
                childColumns = "piece_id",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("part_id"), @Index("piece_id")}
)
public class PartEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "part_id")
    private long partId;

    @ColumnInfo(name = "piece_id")
    private long pieceId;

    @ColumnInfo(name = "order_num")
    private int orderNum;

    @ColumnInfo(name = "part_num")
    private int partNum;

    @ColumnInfo(name = "is_priority")
    private boolean isPriority;

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

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
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

    public PartEntity() {
    }

    @Ignore
    public PartEntity(long pieceId, int partNum, int orderNum, boolean isPriority) {
        this.pieceId = pieceId;
        this.partNum = partNum;
        this.orderNum = orderNum;
        this.isPriority = isPriority;
    }
}
