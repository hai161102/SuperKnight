package com.haiprj.games.superknight.models;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PlayerModel extends BaseModel {

    private Context context;
    private static final String NORMAL = "normal";
    private static final String MOVE_VERTICAL = "move vertical";
    @SuppressLint("StaticFieldLeak")
    private static PlayerModel instance;
    private final HashMap<String, Bitmap[]> animationBitmaps = new HashMap<>();
    private Bitmap currentBitmap;
    private RectF srcRect;
    private RectF dstRect;
    private Thread animationThread;
    private boolean isLeft, isRight, isUp;
    private String currentState = NORMAL;
    private final float ANi_FPS = 12;
    private final Runnable animationRunnable = new Runnable() {
        @SuppressWarnings("BusyWait")
        @Override
        public void run() {
            double drawInterval = 1000000000f / ANi_FPS;
            double nextTime = System.nanoTime() + drawInterval;
            while (animationThread != null) {
                double remainTime = nextTime - System.nanoTime();
                remainTime /= 1000000;

                updateAnimation();

                try {
                    if (remainTime < 0) {
                        remainTime = 0;
                    }

                    Thread.sleep((long) remainTime);
                    nextTime += drawInterval;
                } catch (InterruptedException ignored) {
                    animationThread.interrupt();
                }
            }
        }
    };

    int countAnimation;
    int indexAnimation = 0;
    private Bitmap[] currentBitmaps;
    private void updateAnimation() {

        if (isLeft || isRight) {
            currentState = MOVE_VERTICAL;
            countAnimation = animationBitmaps.get(MOVE_VERTICAL).length;
            currentBitmaps = animationBitmaps.get(MOVE_VERTICAL);
        }
        else {
            currentState = NORMAL;
            countAnimation = animationBitmaps.get(NORMAL).length;
            currentBitmaps = animationBitmaps.get(NORMAL);
        }
        indexAnimation++;
        if (indexAnimation >= countAnimation) indexAnimation = 0;
        currentBitmap = currentBitmaps[indexAnimation];
    }

    public static PlayerModel getInstance() {
        if (instance == null) instance = new PlayerModel();
        return instance;
    }

    public static PlayerModel getInstance(Context context, String name, int width, int height, float x, float y, int... imageId) {
        if (instance == null) instance = new PlayerModel(context,name, width, height, x, y, imageId);
        return instance;
    }

    private PlayerModel(Context context, String name, int width, int height, float x, float y, int... imageId) {
        super(name, width, height, x, y, imageId);
        this.context = context;
        init();
    }

    private void init() {
        this.srcRect = new RectF();
        this.dstRect = new RectF();
        formatSrcRect();
        formatDstRect();
        animationBitmaps.put(NORMAL, new Bitmap[7]);
        animationBitmaps.put(MOVE_VERTICAL, new Bitmap[6]);

        for (int i = 0; i < this.imageId.length; i++) {
            if (i < Objects.requireNonNull(animationBitmaps.get(NORMAL)).length) {
                Objects.requireNonNull(animationBitmaps.get(NORMAL))[i]
                        = BitmapFactory.decodeResource(context.getResources(), this.imageId[i]);
            }
            else {
                Objects.requireNonNull(
                        animationBitmaps.get(MOVE_VERTICAL)
                )[i - Objects.requireNonNull(animationBitmaps.get(NORMAL)).length]
                        = BitmapFactory.decodeResource(context.getResources(), this.imageId[i]);
            }
        }

        animationThread = new Thread(animationRunnable);
        animationThread.start();
    }

    private void formatDstRect() {
        this.dstRect.right = this.dstRect.left + this.width;
        this.dstRect.bottom = this.dstRect.top + this.height;
    }

    private void formatSrcRect() {
        this.srcRect.right = this.srcRect.left + 32;
        this.srcRect.bottom = this.srcRect.top + 32;
    }

    private PlayerModel() {
        this.width = 32;
        this.height = 32;
    }

    public void update() {

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentBitmap, this.x, this.y, null);
    }
}
