package com.musicnator.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "pieces_table")
public class PieceEntity {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "piece_id")
    private long pieceId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "is_active")
    private boolean isActive;

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public PieceEntity(long pieceId, String name, boolean isActive){
        this.pieceId = pieceId;
        this.name = name;
        this.isActive = isActive;
    }

    @Ignore
    public PieceEntity(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    @Ignore
    public PieceEntity(String name) {
        this.name = name;
        this.isActive = true;
    }

    @Ignore
    public PieceEntity() {
    }
}
