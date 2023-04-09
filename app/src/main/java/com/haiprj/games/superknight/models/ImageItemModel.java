package com.haiprj.games.superknight.models;

public class ImageItemModel {
    private int resId;
    private int imageNumber;

    private boolean isChoose;

    public ImageItemModel(int resId, int imageNumber) {
        this.resId = resId;
        this.imageNumber = imageNumber;
    }

    public ImageItemModel() {
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }
}
