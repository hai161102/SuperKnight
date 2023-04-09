package com.haiprj.games.superknight.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.haiprj.games.superknight.base.utils.LoadBitmapUtil;

public class StaticModel extends BaseModel {

    private final Context context;
    private Bitmap[] bitmaps;

    private EntityListener listener;

    private PlayerModel playerModel;

    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    private final LoadBitmapUtil.OnLoadListener loadListener = new LoadBitmapUtil.OnLoadListener() {
        @Override
        public void onLoad() {

        }

        @Override
        public void onDone(Bitmap[] bitmaps) {
            for (int i = 0; i < bitmaps.length; i++) {
                bitmaps[i] = Bitmap.createScaledBitmap(bitmaps[i], StaticModel.this.width, StaticModel.this.height, true);
            }
            StaticModel.this.bitmaps = bitmaps;
        }
    };

    public void setListener(EntityListener listener) {
        this.listener = listener;
    }

    public StaticModel(Context context, String name, int width, int height, float x, float y, int... imageId) {
        super(name, width, height, x, y, imageId);
        this.context = context;
        this.bitmaps = new Bitmap[imageId.length];
        init();
    }

    private void init() {
        new LoadBitmapUtil(this.context, this.loadListener, this.imageId).execute();
    }

    @Override
    public void update() {
        super.update();

        if (playerModel.checkIntersection(this)) {
            playerModel.setFall(false);
        }
        //this.x = this.worldX - playerModel.getWorldX();
        if (this.x - this.width < 0) {
            this.listener.onRemove();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (Bitmap bitmap : bitmaps) {
            if (bitmap != null)
                canvas.drawBitmap(bitmap, this.x, this.y, null);
        }
    }

    public interface EntityListener {
        void onRemove();
    }
}
