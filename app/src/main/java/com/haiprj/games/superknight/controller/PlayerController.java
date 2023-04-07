package com.haiprj.games.superknight.controller;

import android.content.Context;
import android.graphics.Canvas;

import com.haiprj.games.superknight.models.PlayerModel;

public class PlayerController {

    public PlayerController() {
    }

    public void setPlayer(Context context, String name, int width, int height, float x, float y, int... imageId) {
        PlayerModel.getInstance(context, name, width, height, x, y, imageId);
    }

    public void setPlayer() {
        PlayerModel.getInstance();
    }

    public PlayerModel getPlayer() { return PlayerModel.getInstance(); }

    public void update() {
        PlayerModel.getInstance().update();
    }

    public void draw(Canvas canvas) {
        PlayerModel.getInstance().draw(canvas);
    }
}

