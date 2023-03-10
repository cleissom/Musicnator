package com.musicnator.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PieceEntity.class, PartEntity.class, HistoryEntity.class}, version = 7, exportSchema = false)
public abstract class PieceDatabase extends RoomDatabase {
    private static PieceDatabase instance;

    // Define abstract method for DAO
    public abstract PieceDao pieceDao();

    // Get singleton instance of the database
    public static synchronized PieceDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            PieceDatabase.class, "pieces")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
