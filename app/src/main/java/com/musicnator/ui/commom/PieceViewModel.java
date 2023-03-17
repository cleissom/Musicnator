package com.musicnator.ui.commom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.musicnator.database.HistoryEntity;
import com.musicnator.database.Piece;
import com.musicnator.database.PieceDao;
import com.musicnator.database.PieceDatabase;
import com.musicnator.database.PieceEntity;
import com.musicnator.database.PiecePart;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PieceViewModel extends AndroidViewModel {

    private final PieceDao pieceDao;
    private final ExecutorService executorService;

    public PieceViewModel(@NonNull Application application) {
        super(application);
        pieceDao = PieceDatabase.getInstance(application).pieceDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void deleteAll() {
        pieceDao.deleteAll();
    }

    public LiveData<List<PieceEntity>> getAllPieceEntities() {
        return pieceDao.getAllPieceEntities();
    }

    public LiveData<List<Piece>> getAllPieces() {
        return pieceDao.getAllPieces();
    }



    public LiveData<List<PiecePart>> getActivePiecePartsByOrder() {
        return pieceDao.getActivePiecePartsByOrder();
    }

    public void addPiece(Piece piece) {
        executorService.execute(() -> pieceDao.insertPieceWithParts(piece));
    }

    public Integer getHighestActiveOrderNum() {
        return pieceDao.getHighestActiveOrderNum();
    }


    public void setPieceActive(Piece piece, boolean isActive) {
        executorService.execute(() -> pieceDao.setPieceActiveById(piece.piece.getPieceId(), isActive));
    }

    public void setPiecePartPriority(PiecePart piece) {
        executorService.execute(() -> {
                    pieceDao.clearPriority();
                    pieceDao.setPartPriorityById(piece.getPartId());
                }
        );
    }

    public void deletePiecePart(PiecePart piecePart) {
        executorService.execute(() -> {
            pieceDao.deletePieceById(piecePart.getPieceId());
            reorderParts();
        });
    }

    private void reorderParts(){
        executorService.execute(() -> {
            List<PiecePart> pieceParts = pieceDao.getActivePiecePartsByOrderOnce();
            for(int i = 0; i < pieceParts.size(); i++){
                pieceDao.setPartOrderNum(pieceParts.get(i).getPartId(), i);
            }
        });
    }

    public void setPiecePartOrderNum(PiecePart piecePart, int orderNum) {
        executorService.execute(() -> pieceDao.setPartOrderNum(piecePart.getPartId(), orderNum));
    }

    public void setPiecePartCompletedToday(PiecePart piecePart) {
        executorService.execute(() -> pieceDao.insertHistoryEntity(new HistoryEntity(piecePart.getPartId(), DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .withZone(ZoneId.systemDefault())
                .format(Instant.now()))));
    }

    public void setPiecePartNotCompletedToday(PiecePart piecePart) {
        executorService.execute(() -> {
            long historyId = pieceDao.getTodayHistoryIdByPartId(piecePart.getPartId());
                pieceDao.deleteHistoryById(historyId);
        });
    }

    public LiveData<List<PiecePart>> getSessionPieceParts() {
        return pieceDao.getSessionPieceParts();
    }
}
