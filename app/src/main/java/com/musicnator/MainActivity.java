package com.musicnator;

import static java.lang.Thread.sleep;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.musicnator.database.PieceDatabase;
import com.musicnator.ui.commom.PieceAdapter;
import com.musicnator.ui.commom.PieceViewModel;

import de.raphaelebner.roomdatabasebackup.core.RoomBackup;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView2;
    private PieceAdapter mAdapter;

    private PieceViewModel pieceViewModel;


    private RoomBackup mRoomBackup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_session, R.id.navigation_log)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



//        mRecyclerView = findViewById(R.id.recycler_view);
//        mRecyclerView2 = findViewById(R.id.recycler_view2);
//
//        mAdapter = new PieceAdapter();
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(mAdapter, MainActivity.this));
//        itemTouchHelper.attachToRecyclerView(mRecyclerView);
//
//        pieceViewModel = new ViewModelProvider(this).get(PieceViewModel.class);
//        pieceViewModel.getAllPieceParts().observe(this, pieceParts -> {
//            mAdapter.setPieces(pieceParts);
//        });
//
//
//        PieceAdapter adapter = new PieceAdapter();
//        mRecyclerView2.setAdapter(adapter);
//        mRecyclerView2.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        ItemTouchHelper itemTouchHelper2 = new ItemTouchHelper(new SwipeToDeleteCallback(adapter, MainActivity.this));
//        itemTouchHelper2.attachToRecyclerView(mRecyclerView2);
//
//        pieceViewModel.getActivePiecePartsByOrder().observe(this, adapter::setPieces);

//        AsyncTask.execute(() -> {
//            pieceViewModel.deleteAll();
//        });

//        new Handler().postDelayed(() -> AsyncTask.execute(() -> {
//            Piece pieceA = new Piece("Piece A", 3, 0);
//            pieceA.parts.get(0).addDate("2023-02-10");
//            pieceA.parts.get(0).addDate("2023-02-03");
//            pieceA.parts.get(1).addDate("2023-02-05");
//            pieceA.parts.get(1).addDate("2023-02-02");
//            pieceA.parts.get(2).addDate("2023-02-04");
//            pieceA.parts.get(2).addDate("2023-02-02");
//            pieceViewModel.addPiece(pieceA);
//            Piece pieceB = new Piece("Piece B", 2, 3);
//            pieceB.parts.get(0).addDate("2023-02-07");
//            pieceB.parts.get(0).addDate("2023-02-04");
//            pieceB.parts.get(1).addDate("2023-02-06");
//            pieceB.parts.get(1).addDate("2023-02-02");
//            pieceViewModel.addPiece(pieceB);
//            Piece pieceC = new Piece("Piece C", 2, 5);
//            pieceC.parts.get(0).addDate("2023-02-05");
//            pieceC.parts.get(0).addDate("2023-02-04");
//            pieceC.parts.get(1).addDate("2023-02-05");
//            pieceC.parts.get(1).addDate("2023-02-03");
//            pieceViewModel.addPiece(pieceC);
//        }), 1000);

        mRoomBackup = new RoomBackup(MainActivity.this);
        mRoomBackup.database(PieceDatabase.getInstance(MainActivity.this));
        mRoomBackup.maxFileCount(3);
        mRoomBackup.onCompleteListener((success, message, exitCode) -> {
            Log.d("asdasf", "success: " + success + ", message: " + message + ", exitCode: " + exitCode);
            if (success) finish();
        });



    }

    void CreateData(String pieceName, int partsNum) {
        PieceDatabase database = PieceDatabase.getInstance(getApplicationContext());


//        PieceEntity piece = new PieceEntity(pieceName);
//        long pieceId = database.pieceDao().insertPiece(piece);
//        piece.setPieceId(pieceId);
//
//        List<PartEntity> parts = new ArrayList<>();
//        for (int i = 0; i < partsNum; i++){
//            PartEntity part = new PartEntity(pieceId, i, false);
//            long partId = database.pieceDao().insertPart(part);
//            part.setPartId(partId);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup_database:
                mRoomBackup.backup();
                return true;

            case R.id.restore_database:
                mRoomBackup.restore();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

//        @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
//    }
}


