package com.haiprj.games.superknight.models;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.haiprj.games.superknight.R;
import com.haiprj.games.superknight.base.BaseAnimation;
import com.haiprj.games.superknight.base.utils.GameUtils;
import com.haiprj.games.superknight.base.utils.LoadBitmapUtil;

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
    private boolean isLeft, isRight, isUp;
    private boolean isFall = true;
    private PlayerDirection direction;
    @SuppressWarnings("FieldCanBeLocal")
    private String currentState = NORMAL;
    @SuppressWarnings("FieldCanBeLocal")
    private final int ANi_FPS = 12;

    @SuppressWarnings("FieldCanBeLocal")
    private PlayerAnimation playerAnimation;

    int countAnimation;
    int indexAnimation = 0;
    @SuppressWarnings("FieldCanBeLocal")
    private Bitmap[] currentBitmaps;
    private Bitmap[] allBitmaps;
    private final LoadBitmapUtil.OnLoadListener loadListener = new LoadBitmapUtil.OnLoadListener() {
        @Override
        public void onLoad() {

        }

        @Override
        public void onDone(Bitmap[] bitmaps) {
            PlayerModel.this.allBitmaps = bitmaps;
            for (int i = 0; i < allBitmaps.length; i++) {
                if (i < Objects.requireNonNull(animationBitmaps.get(NORMAL)).length) {
                    Objects.requireNonNull(animationBitmaps.get(NORMAL))[i]
                            = Bitmap.createScaledBitmap(allBitmaps[i], PlayerModel.this.width, PlayerModel.this.height, true);
                }
                else {
                    Objects.requireNonNull(
                            animationBitmaps.get(MOVE_VERTICAL)
                    )[i - Objects.requireNonNull(animationBitmaps.get(NORMAL)).length]
                            = Bitmap.createScaledBitmap(allBitmaps[i], PlayerModel.this.width, PlayerModel.this.height, true);
                }
            }
        }
    };
    private RectF cameraRect;
    private RectF worldRect;
    private float playerSpeed;

    public void setPlayerSpeed(float playerSpeed) {
        this.playerSpeed = playerSpeed;
    }

    @SuppressWarnings("ConstantConditions")
    private void updateAnimation() {

        if (isLeft || isRight) {
            countAnimation = animationBitmaps.get(MOVE_VERTICAL).length;
            currentBitmaps = animationBitmaps.get(MOVE_VERTICAL);

        }
        else {
            countAnimation = animationBitmaps.get(NORMAL).length;
            currentBitmaps = animationBitmaps.get(NORMAL);
        }
        indexAnimation++;
        if (indexAnimation >= countAnimation) indexAnimation = 0;
        Bitmap cacheBitmap = currentBitmaps[indexAnimation];
        if (direction == PlayerDirection.LEFT) {
            cacheBitmap = GameUtils.createFlippedBitmap(cacheBitmap, true, false);
        }

        currentBitmap = cacheBitmap;
    }

    public static PlayerModel getInstance() {
        if (instance == null) instance = new PlayerModel();
        return instance;
    }

    @SuppressWarnings("UnusedReturnValue")
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
        new LoadBitmapUtil(this.context, loadListener, this.imageId).execute();


        playerAnimation = new PlayerAnimation(ANi_FPS, new BaseAnimation.AnimationActionListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onUpdate() {
                updateAnimation();
            }

            @Override
            public void onEnd() {

            }
        });

        playerAnimation.start();
    }

    public void left() {
        this.isLeft = true;
        this.isRight = false;
        currentState = MOVE_VERTICAL;
        direction = PlayerDirection.LEFT;

    }

    public void right() {
        this.isRight = true;
        this.isLeft = false;
        currentState = MOVE_VERTICAL;
        direction = PlayerDirection.RIGHT;

    }

    public void jump() {
        this.isUp = true;
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

    @Override
    public void update() {
//        this.worldY += 1;
        //this.worldX += playerSpeed;
//        if (this.worldRect.right - this.worldX <= this.cameraRect.right - this.x){
//            if (isRight)
//                this.x += playerSpeed;
//            else if (isLeft)
//                this.x -= playerSpeed;
//        }
//        if (this.worldX - this.worldRect.left <= this.x - this.cameraRect.left) {
//            if (isRight)
//                this.x += playerSpeed;
//            else if (isLeft)
//                this.x -= playerSpeed;
//        }
        if (isLeft) {
            this.x -= playerSpeed;
        }
        else if (isRight) {
            this.x += playerSpeed;
        }
        if (isUp) {
            isFall = false;
            this.y -= 15;
        }
        if (isFall)
            this.y += 10f;
//        this.y += 10;
    }

    public void setFall(boolean fall) {
        isFall = fall;
    }

    @Override
    public void draw(Canvas canvas) {
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.FILL);
        if (currentBitmap != null){
            //canvas.drawRect(getRect(), paint);
            canvas.drawBitmap(currentBitmap, this.x, this.y, null);

        }
    }

    public boolean checkIntersection(BaseModel model) {
        return this.getRect().intersect(model.getRect());
    }

    public void setupCameraRect(float left, float top, float right, float bottom) {
        this.cameraRect = new RectF(left, top, right, bottom);
        this.x = this.cameraRect.left + this.cameraRect.width() / 2f;
        this.y = this.cameraRect.bottom - this.cameraRect.height() / 3f;
    }

    public void setupCameraRect(RectF cameraRect) {
        this.cameraRect = cameraRect;
        this.x = this.cameraRect.left + this.cameraRect.width() / 2f;
        this.y = this.cameraRect.bottom - this.cameraRect.height() / 3f;
    }
    public void setWorldRect(RectF worldRect) {
        this.worldRect = worldRect;
    }

    public void setWorldRect(float l, float t, float r, float b) {
        this.worldRect = new RectF(l, t, r, b);
    }

    public void clearFocus() {
        this.isLeft = this.isRight = this.isUp = false;
        currentState = NORMAL;

    }

    @SuppressWarnings("InnerClassMayBeStatic")
    class PlayerAnimation extends BaseAnimation {

        public PlayerAnimation(int FPS, BaseAnimation.AnimationActionListener listener) {
            super(FPS, listener);
        }
    }

    enum PlayerDirection {
        LEFT,
        RIGHT
    }

}
