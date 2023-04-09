package com.haiprj.games.superknight.actions;

import android.graphics.Canvas;
import android.graphics.RectF;

public abstract class BaseAction {

    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected int[] images;

    public BaseAction(float x, float y, int width, int height, int... images) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.images = images;
    }

    public BaseAction() {
    }

    public void draw(Canvas canvas){

    }

    public void update() {

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getImages() {
        return images;
    }

    public void setImages(int... images) {
        this.images = images;
    }

    public RectF getArea() {
        return new RectF(x, y, x + width, y + height);
    }
}
