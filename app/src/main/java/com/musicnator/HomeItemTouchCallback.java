package com.musicnator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.musicnator.database.PiecePart;

public class HomeItemTouchCallback extends ItemTouchHelper.SimpleCallback {

    private final PieceAdapter mAdapter;
    private final PieceViewModel mPieceViewModel;
    private final FragmentActivity mContext;

    public HomeItemTouchCallback(PieceAdapter adapter, FragmentActivity context) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mAdapter = adapter;
        mContext = context;
        mPieceViewModel = new ViewModelProvider(context).get(PieceViewModel.class);;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        final int fromPos = viewHolder.getAdapterPosition();
        final int toPos = target.getAdapterPosition();
        mPieceViewModel.setPiecePartOrderNum(mAdapter.getPieceAt(fromPos),toPos);
        mPieceViewModel.setPiecePartOrderNum(mAdapter.getPieceAt(toPos),fromPos);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        PiecePart piecePart = mAdapter.getPieceAt(position);
        switch (direction){
            case ItemTouchHelper.LEFT:
                if (!piecePart.isPriority()){
                    mPieceViewModel.setPiecePartPriority(piecePart);
                } else {
                    mAdapter.notifyItemChanged(position);
                }
                break;
            case ItemTouchHelper.RIGHT:
                showDeleteDialog(piecePart);
                mAdapter.notifyItemChanged(position);
                break;
            default:
                break;
        }
    }

    void showDeleteDialog(PiecePart piecePart){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Are you sure you want to delete?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mPieceViewModel.deletePiecePart(piecePart);
//                                    getActivity().finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

//    @Override
//    public void onChildDraw (Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,float dX, float dY,int actionState, boolean isCurrentlyActive){
//        final ColorDrawable background = new ColorDrawable(Color.RED);
//        View itemView = viewHolder.itemView;
//        background.setBounds(0, itemView.getTop(), (int) (itemView.getLeft() + dX), itemView.getBottom());
//        background.draw(c);
//
//        super.onChildDraw(c, recyclerView, viewHolder, dX/4, dY, actionState, isCurrentlyActive);
//    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        Bitmap icon;

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

            View itemView = viewHolder.itemView;
            float height = (float) itemView.getBottom() - (float) itemView.getTop();
            float width = height / 3;
            
            Paint p = new Paint();

            if (dX > 0) {
                p.setColor(Color.parseColor("#388E3C"));
                RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                c.drawRect(background, p);
//                icon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_home_black_24dp);
                Drawable d = AppCompatResources.getDrawable(mContext, R.drawable.ic_dashboard_black_24dp);

                RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
//                c.drawBitmap(d, null, icon_dest, p);
                assert d != null;
                d.setBounds(new Rect((int) (itemView.getLeft() + width), (int) (itemView.getTop() + width), (int) (itemView.getLeft() + 2 * width), (int) (itemView.getBottom() - width)));
                d.draw(c);
            } else if (dX < 0) {
                p.setColor(Color.parseColor("#D32F2F"));
                RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                c.drawRect(background, p);
//                icon = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_dashboard_black_24dp);
                RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
//                c.drawBitmap(icon, null, icon_dest, p);
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}