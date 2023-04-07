package com.haiprj.games.superknight.models;

public abstract class BaseModel {
    protected String name;
    protected int width;
    protected int height;
    protected float x;
    protected float y;
    protected int[] imageId;
    protected float worldX = 0f;
    protected float worldY = 0f;

    public BaseModel(String name, int width, int height, float x, float y, int... imageId) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.imageId = imageId;
        this.worldX = this.x;
        this.worldY = this.y;
    }

    public BaseModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int[] getImageId() {
        return imageId;
    }

    public void setImageId(int... imageId) {
        this.imageId = imageId;
    }

    public float getWorldX() {
        return worldX;
    }

    public void setWorldX(float worldX) {
        this.worldX = worldX;
    }

    public float getWorldY() {
        return worldY;
    }

    public void setWorldY(float worldY) {
        this.worldY = worldY;
    }
}
