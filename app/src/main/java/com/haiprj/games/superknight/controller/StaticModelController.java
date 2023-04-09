package com.haiprj.games.superknight.controller;

import android.content.Context;
import android.graphics.Canvas;

import com.haiprj.games.superknight.R;
import com.haiprj.games.superknight.models.PlayerModel;
import com.haiprj.games.superknight.models.StaticModel;

public class StaticModelController {

    private final Context context;
    private final int SCREEN_WIDTH;
    private final int SCREEN_HEIGHT;

    private PlayerController playerController;
    private final StaticModel[] staticModels = new StaticModel[20];

    public StaticModelController(Context context, int sWidth, int sHeight, PlayerController playerController) {
        this.context = context;
        this.SCREEN_WIDTH = sWidth;
        this.SCREEN_HEIGHT = sHeight;
        this.playerController = playerController;
        for (int i = 0; i < staticModels.length; i++) {
            staticModels[i] = getNewModel(i);
        }
    }


    private StaticModel getNewModel(int i) {

        int size = 64;
        float entityX;
        float entityY = SCREEN_HEIGHT - size;
        if (i <= 0) {
            entityX = 0;
        }
        else {
            entityX = staticModels[i - 1].getX() + size;
        }
        StaticModel staticModel = new StaticModel(
                this.context,
                "Static Entity " + i,
                64,
                64,
                entityX,
                entityY,
                R.drawable.wall_up
        );
        staticModel.setListener(() -> {
            if (staticModels.length - 1 - i >= 0)
                System.arraycopy(staticModels, i + 1, staticModels, i, staticModels.length - 1 - i);
            staticModels[staticModels.length - 1] = getNewModel(staticModels.length - 1);
        });
        staticModel.setPlayerModel(playerController.getPlayer());
        return staticModel;
    }

    public void draw(Canvas canvas) {
        for (StaticModel st: staticModels) {
            st.draw(canvas);
        }
    }

    public void update() {
        playerController.getPlayer().setFall(true);
        for (StaticModel st: staticModels) {
            st.update();
        }
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }
}
