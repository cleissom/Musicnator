package com.musicnator.database;

import androidx.room.ColumnInfo;

public class PiecePartListItem {
    @ColumnInfo(name = "part_id")
    private long partId;

    @ColumnInfo(name = "name")
    private String pieceName;

    @ColumnInfo(name = "part_num")
    private int partNum;

    @ColumnInfo(name = "done_today")
    private boolean isDoneToday;

    @ColumnInfo(name = "is_priority")
    private boolean isPriority;
}
