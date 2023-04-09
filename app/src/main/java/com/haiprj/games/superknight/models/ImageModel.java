package com.haiprj.games.superknight.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.haiprj.games.superknight.base.utils.LoadBitmapUtil;
import com.haiprj.games.superknight.ui.widget.CreateMapSurface;

public class ImageModel extends BaseModel{

    private final Context context;
    private CreateMapSurface surface;
    private Bitmap bitmap;

    private Paint outline;
    private LoadBitmapUtil.OnLoadListener loadListener = new LoadBitmapUtil.OnLoadListener() {
        @Override
        public void onLoad() {

        }

        @Override
        public void onDone(Bitmap[] bitmaps) {
            bitmap = bitmaps[0];
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        }
    };
    private boolean isChoose;

    public void setSurface(CreateMapSurface surface) {
        this.surface = surface;
    }

    public ImageModel(Context context, String name, int width, int height, float x, float y, int... imageId) {
        super(name, width, height, x, y, imageId);
        this.context = context;
        if (imageId != null)
            new LoadBitmapUtil(this.context, loadListener, imageId).execute();
        outline = new Paint();
        outline.setStyle(Paint.Style.STROKE);
        outline.setStrokeWidth(1f);
        outline.setColor(Color.WHITE);
    }

    public ImageModel(Context context) {
        this.context = context;
    }

    @Override
    public void setImageId(int... imageId) {
        super.setImageId(imageId);
        new LoadBitmapUtil(this.context, loadListener, imageId).execute();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, this.x, this.y, null);
        }
        if (isChoose) {
            outline.setColor(Color.RED);
        }
        else {
            outline.setColor(Color.WHITE);
        }
        canvas.drawRect(this.getRect(), outline);

    }

    @Override
    public void update() {
        super.update();
    }

    public boolean touchDown(MotionEvent event) {
        if (this.getRect().contains(event.getX(), event.getY())) {
            isChoose = !isChoose;
            if (!isChoose) {
                this.name = "";
                imageId = null;
                bitmap = null;
            }
            return true;
        }

        return false;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public boolean isChoose() {
        return isChoose;
    }
}
