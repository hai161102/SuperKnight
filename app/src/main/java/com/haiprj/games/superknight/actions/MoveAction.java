package com.haiprj.games.superknight.actions;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import com.haiprj.games.superknight.actions.interfaces.MoveListener;
import com.haiprj.games.superknight.base.utils.LoadBitmapUtil;

public class MoveAction extends BaseAction {
    private final Context context;
    private LoadBitmapUtil loadBitmapUtil;
    private RectF moveAreaUp;
    private RectF moveAreaLeft;
    private RectF moveAreaRight;
    private MoveListener listener;

    private LoadBitmapUtil.OnLoadListener loadListener = new LoadBitmapUtil.OnLoadListener() {
        @Override
        public void onLoad() {

        }

        @Override
        public void onDone(Bitmap[] bitmaps) {
            MoveAction.this.bitmaps = bitmaps;
            setDefault();
            try {
                MoveAction.this.bitmaps[0] = Bitmap.createScaledBitmap(bitmaps[0], (int) moveAreaUp.width(), (int) moveAreaUp.height(), true);
                MoveAction.this.bitmaps[1] = Bitmap.createScaledBitmap(bitmaps[1], (int) moveAreaLeft.width(), (int) moveAreaLeft.height(), true);
                MoveAction.this.bitmaps[2] = Bitmap.createScaledBitmap(bitmaps[2], (int) moveAreaRight.width(), (int) moveAreaRight.height(), true);
            } catch (Exception e)   {
            }

        }
    };
    private Bitmap[] bitmaps;

    public MoveAction(Context context, float x, float y, int width, int height, int... images) {
        super(x, y, width, height, images);
        this.context = context;
        init();
    }

    private void init() {
        bitmaps = new Bitmap[images.length];
        moveAreaUp = new RectF();
        moveAreaLeft = new RectF();
        moveAreaRight = new RectF();
        loadBitmapUtil = new LoadBitmapUtil(this.context, loadListener, images);
        loadBitmapUtil.execute();

    }

    public MoveAction(Context context) {
        this.context = context;
        bitmaps = new Bitmap[0];
        moveAreaUp = new RectF();
        moveAreaLeft = new RectF();
        moveAreaRight = new RectF();
    }

    private void setDefault() {
        moveAreaUp.top = getArea().top;
        moveAreaUp.bottom = getArea().centerY() - 20f;
        moveAreaUp.left = getArea().centerX() - 20f;
        moveAreaUp.right = getArea().centerX() + 20f;

        moveAreaLeft.left = getArea().left;
        moveAreaLeft.top = getArea().centerY() - 20f;
        moveAreaLeft.bottom = getArea().centerY() + 20f;
        moveAreaLeft.right = getArea().centerX() - 20f;

        moveAreaRight.right = getArea().right;
        moveAreaRight.left = getArea().centerX() + 20f;
        moveAreaRight.top = getArea().centerY() - 20f;
        moveAreaRight.bottom = getArea().centerY() + 20f;

    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        if (bitmaps[0] != null){
            //canvas.drawRect(getArea(), paint);
            canvas.drawBitmap(bitmaps[0], moveAreaUp.left, moveAreaUp.top, null);

        }
        paint.setColor(Color.RED);
        if (bitmaps[1] != null){
            //canvas.drawRect(moveArea, paint);
            canvas.drawBitmap(bitmaps[1], this.moveAreaLeft.left, this.moveAreaLeft.top, null);

        }

        if (bitmaps[2] != null){
            //canvas.drawRect(moveArea, paint);
            canvas.drawBitmap(bitmaps[2], this.moveAreaRight.left, this.moveAreaRight.top, null);

        }

    }

    @Override
    public void update() {
        super.update();
    }

    public void moveTouchDown(MotionEvent event) {
        if (moveAreaUp.contains(event.getX(), event.getY())) {
            listener.onMoveUp();
        }
        else if (moveAreaLeft.contains(event.getX(), event.getY())) {
            listener.onMoveLeft();
        }
        else if (moveAreaRight.contains(event.getX(), event.getY())) {
            listener.onMoveRight();
        }
    }

    private float[][][] points = {
            {
                    {2, -1}, {2, 1}
            },
            {
                    {2, 1}, {1, 1}
            },
            {
                    {1, 2}, {-1, 2}
            },
            {
                    {-1, 2}, {1, -2}
            },
            {
                    {-2, 1}, {-2, -1}
            }
    };
    public void move(MotionEvent event) {

    }

    public void moveTouchUp(MotionEvent event) {
        clearFocus();
    }

    private void clearFocus() {
        listener.onTouchUp();
        setDefault();
    }

    public void setListener(MoveListener listener) {
        this.listener = listener;
    }
}
