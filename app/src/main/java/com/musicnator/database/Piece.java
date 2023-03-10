package com.musicnator.database;

import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class Piece {
    @Embedded public PieceEntity piece;
    @Relation(
            parentColumn = "piece_id",
            entityColumn = "piece_id",
            entity = PartEntity.class
    )
    public List<Part> parts;

    public Piece(){}

    @Ignore
    public Piece(String name, int parts, int startOrder){
        piece = new PieceEntity(name);
        this.parts = new ArrayList<>();
        for (int i = 0; i < parts; i++){
            this.parts.add(new Part(0, i+1, startOrder+i, false));
        }
    }
}
