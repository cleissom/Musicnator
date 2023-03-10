package com.musicnator.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class Part {
    @Embedded
    public PartEntity part;
    @Relation(
            parentColumn = "part_id",
            entityColumn = "part_id"
    )
    public List<HistoryEntity> history;

    public Part(){}

    public Part(long pieceId, int partNum, int orderNum, boolean isPriority) {
        this.part = new PartEntity(pieceId, partNum, orderNum, isPriority);
        this.history = new ArrayList<>();
    }

    public void addDate(String date){
        this.history.add(new HistoryEntity(this.part.getPieceId(), date));
    }
}