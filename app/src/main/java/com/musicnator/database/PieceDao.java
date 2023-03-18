package com.musicnator.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PieceDao {

    @Query("DELETE FROM pieces_table")
    void deleteAll();

    @Transaction
    @Query("SELECT * FROM pieces_table")
    LiveData<List<PieceEntity>> getAllPieceEntities();

    @Transaction
    @Query("SELECT * FROM pieces_table WHERE piece_id = :pieceId")
    LiveData<PieceEntity> getPieceEntityById(int pieceId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPieceEntity(PieceEntity piece);

    @Update
    int updatePieceEntity(PieceEntity piece);

    /////////////////////////////////////////////////
    @Transaction
    @Query("SELECT * FROM pieces_table")
    LiveData<List<Piece>> getAllPieces();

    @Transaction
    @Query("UPDATE pieces_table SET is_active = :isActive WHERE piece_id = :id")
    void setPieceActiveById(long id, boolean isActive);

    @Transaction
    @Query("UPDATE parts_table SET order_num = :orderNum WHERE part_id = :id")
    int setPartOrderNum(long id, int orderNum);

    /////////////////////////////////////////////////
    @Transaction
    @Query("SELECT * FROM parts_table")
    LiveData<List<PartEntity>> getAllPartsEntity();

    @Transaction
    @Query("SELECT * FROM parts_table WHERE piece_id = :pieceId")
    LiveData<List<PartEntity>> getPartEntitiesByPieceId(int pieceId);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPartEntity(PartEntity part);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertParts(List<PartEntity> parts);

    @Update
    int updatePart(PartEntity part);

    @Transaction
    @Query("SELECT * FROM history_table")
    LiveData<List<HistoryEntity>> getAllHistoryEntities();

    /////////////////////////////////////////////////


    @Transaction
    @Query("WITH next_order_num AS (\n" +
            "SELECT order_num\n" +
            "FROM parts_table\n" +
            "INNER JOIN pieces_table USING (piece_id)\n" +
            "LEFT JOIN (\n" +
            "  SELECT part_id, MAX(date) as date\n" +
            "  FROM parts_table\n" +
            "  INNER JOIN pieces_table USING (piece_id)\n" +
            "  LEFT JOIN history_table USING (part_id)\n" +
            "  WHERE is_active = 1 AND date < DATE('now','localtime')\n" +
            "  GROUP BY part_id\n" +
            "  ) USING (part_id)\n" +
            "ORDER BY date ASC, order_num ASC LIMIT 1\n" +
            ")\n" +
            "\n" +
            "SELECT part_id, piece_id, name, part_num, order_num, is_priority, is_done_today, is_active\n" +
            "FROM (\n" +
            "  SELECT *\n" +
            "  FROM parts_table\n" +
            "  INNER JOIN pieces_table USING (piece_id)\n" +
            "  LEFT JOIN history_table USING (part_id)\n" +
            "  WHERE is_active = 1\n" +
            "  GROUP BY part_id\n" +
            "  ) a\n" +
            "INNER JOIN (\n" +
            "  SELECT part_id, MAX(date IS DATE('now','localtime')) AS is_done_today\n" +
            "  FROM parts_table\n" +
            "  INNER JOIN pieces_table USING (piece_id)\n" +
            "  LEFT JOIN history_table USING (part_id)\n" +
            "  WHERE is_active = 1\n" +
            "  GROUP BY part_id\n" +
            "  ) b USING (part_id)\n" +
            "ORDER BY is_priority DESC, CASE \n" +
            "        WHEN order_num >= (SELECT order_num FROM next_order_num) AND order_num <= (SELECT MAX(order_num) FROM parts_table) THEN order_num \n" +
            "        WHEN order_num < (SELECT order_num FROM next_order_num) THEN order_num + (SELECT MAX(order_num) FROM parts_table) + 1 \n" +
            "        ELSE order_num - (SELECT MAX(order_num) FROM parts_table) - 1 \n" +
            "        END")
    LiveData<List<PiecePart>> getSessionPieceParts();

    @Transaction
    @Query("SELECT part_id, piece_id, name, part_num, order_num, is_priority, is_done_today, is_active\n" +
            "FROM (\n" +
            "    SELECT *\n" +
            "    FROM parts_table\n" +
            "    INNER JOIN pieces_table USING (piece_id)\n" +
            "    LEFT JOIN history_table USING (part_id)\n" +
            "    WHERE is_active = 1\n" +
            "    GROUP BY part_id\n" +
            ") a \n" +
            "INNER JOIN (\n" +
            "    SELECT part_id, MAX(date IS date('now','localtime')) as is_done_today\n" +
            "    FROM parts_table\n" +
            "    INNER JOIN pieces_table USING (piece_id)\n" +
            "    LEFT JOIN history_table USING (part_id)\n" +
            "    WHERE is_active = 1\n" +
            "    GROUP BY part_id\n" +
            ") b USING (part_id)\n" +
            "ORDER BY order_num ASC")
    LiveData<List<PiecePart>> getActivePiecePartsByOrder();

    @Transaction
    @Query("SELECT part_id, piece_id, name, part_num, order_num, is_priority, is_done_today, is_active\n" +
            "FROM (\n" +
            "    SELECT *\n" +
            "    FROM parts_table\n" +
            "    INNER JOIN pieces_table USING (piece_id)\n" +
            "    LEFT JOIN history_table USING (part_id)\n" +
            "    WHERE is_active = 1\n" +
            "    GROUP BY part_id\n" +
            ") a \n" +
            "INNER JOIN (\n" +
            "    SELECT part_id, MAX(date IS date('now','localtime')) as is_done_today\n" +
            "    FROM parts_table\n" +
            "    INNER JOIN pieces_table USING (piece_id)\n" +
            "    LEFT JOIN history_table USING (part_id)\n" +
            "    WHERE is_active = 1\n" +
            "    GROUP BY part_id\n" +
            ") b USING (part_id)\n" +
            "ORDER BY order_num ASC")
    List<PiecePart> getActivePiecePartsByOrderOnce();

    @Transaction
    @Query("SELECT history_id, part_id, piece_id, name, part_num, is_priority, is_active, date \n" +
            " FROM parts_table\n" +
            "  INNER JOIN pieces_table USING (piece_id)\n" +
            "  INNER JOIN history_table USING (part_id)\n")
    LiveData<List<HistoryPart>> getAllHistory();


    @Transaction
    @Query("UPDATE parts_table SET is_priority = 0 WHERE is_priority = 1")
    void clearPriority();

    @Transaction
    @Query("UPDATE parts_table SET is_priority = 1 WHERE part_id = :id")
    void setPartPriorityById(long id);

    @Transaction
    @Query("DELETE FROM pieces_table WHERE piece_id = :id")
    void deletePieceById(long id);

    @Transaction
    @Query("SELECT history_id FROM parts_table LEFT JOIN history_table USING(part_id) WHERE date = date('now', 'localtime') AND part_id = :id")
    long getTodayHistoryIdByPartId(long id);

    @Transaction
    @Query("DELETE FROM history_table WHERE history_id = :id")
    void deleteHistoryById(long id);

    @Transaction
    @Query("SELECT order_num FROM parts_table INNER JOIN pieces_table USING (piece_id) WHERE is_active = 1 ORDER BY order_num DESC LIMIT 1")
    Integer getHighestActiveOrderNum();


    @Insert
    long insertHistoryEntity(HistoryEntity history);

    /////////////////////////////////////////////////

    @Transaction
    default void insertPieceWithParts(Piece piece) {
        long pieceId = insertPieceEntity(piece.piece);
        for (Part part : piece.parts) {
            part.part.setPieceId(pieceId);
            long partId = insertPartEntity(part.part);
            for (HistoryEntity history : part.history){
                history.setPartId(partId);
                insertHistoryEntity(history);
            }
        }
    }
}
