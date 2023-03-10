package com.musicnator.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "history_table",
        foreignKeys = @ForeignKey(
                entity = PartEntity.class,
                parentColumns = "part_id",
                childColumns = "part_id",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("history_id"), @Index("part_id")}
)
public class HistoryEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "history_id")
    private long historyId;

    @ColumnInfo(name = "part_id")
    private long partId;

    @ColumnInfo(name = "date")
    private String date;

    public HistoryEntity(){}

    @Ignore
    public HistoryEntity(long partId, String date){
        this.partId = partId;
        this.date = date;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
