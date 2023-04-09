package com.haiprj.games.superknight.controller;

import android.content.Context;
import android.graphics.Canvas;

import com.haiprj.games.superknight.actions.MoveAction;

public class MoveActionController {

    private MoveAction moveAction;
    public MoveActionController() {
    }

    public void setMoveAction(MoveAction moveAction) {
        this.moveAction = moveAction;
    }

    public MoveAction getMoveAction() {
        return moveAction;
    }

    public void update() {
        moveAction.update();
    }

    public void draw(Canvas canvas) {
        moveAction.draw(canvas);
    }

}
