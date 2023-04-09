package com.haiprj.games.superknight.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.haiprj.games.superknight.base.widget.BaseGameSurface;
import com.haiprj.games.superknight.controller.ImageController;
import com.haiprj.games.superknight.models.ImageItemModel;

import java.io.File;

public class CreateMapSurface extends BaseGameSurface {

    private ImageController imageController;
    private ImageItemModel currentModel;


    public CreateMapSurface(Context context) {
        super(context);
    }

    public CreateMapSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CreateMapSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CreateMapSurface(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initView(SurfaceHolder surfaceHolder) {
        imageController = new ImageController(getContext(), this);
        imageController.setMatrix(64, 16);
    }

    @Override
    protected void update() {
        if (currentModel != null)
            imageController.update(currentModel);
    }

    @Override
    protected void gameDraw(Canvas canvas) {
        imageController.draw(canvas);
    }

    @Override
    protected void onTouchDown(View view, MotionEvent event) {
        super.onTouchDown(view, event);
        imageController.onTouchDown(event);
    }

    public void setCurrentModel(ImageItemModel model) {
        currentModel = model;
    }

    public void writeFileTo(File file) {
        imageController.writeFileTo(file);
    }
}
