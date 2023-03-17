package com.musicnator.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.musicnator.ui.commom.PieceAdapter;
import com.musicnator.ui.commom.PieceViewModel;
import com.musicnator.R;
import com.musicnator.database.Piece;

public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private PieceAdapter mAdapter;
    private PieceViewModel mPieceViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mPieceViewModel = new ViewModelProvider(requireActivity()).get(PieceViewModel.class);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler_view_order);

        mAdapter = new HomePieceAdapter(requireActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new HomeItemTouchCallback(mAdapter, requireActivity()));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mPieceViewModel.getActivePiecePartsByOrder().observe(requireActivity(), pieceParts -> {
            mAdapter.updatePieces(pieceParts);
        });

        FloatingActionButton fabButton = view.findViewById(R.id.fab_home_add);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Add New Piece");

        View view = getLayoutInflater().inflate(R.layout.dialog_input, null);

        final EditText pieceNameEditText = view.findViewById(R.id.piece_name_edit_text);
        final NumberPicker partsNumPicker = view.findViewById(R.id.parts_num_picker);
        partsNumPicker.setMinValue(1);
        partsNumPicker.setMaxValue(5);

        builder.setView(view);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String pieceName = pieceNameEditText.getText().toString();
                int partsNum = partsNumPicker.getValue();

                AsyncTask.execute(() -> {
                    Integer highestOrderNum = mPieceViewModel.getHighestActiveOrderNum();
                    int startOrder = highestOrderNum != null ? highestOrderNum+1 : 0;
                    mPieceViewModel.addPiece(new Piece(pieceName, partsNum, startOrder));
                });
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}