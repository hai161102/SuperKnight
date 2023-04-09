package com.haiprj.games.superknight.actions.interfaces;

public interface MoveListener extends ActionListener{

    void onMoveUp();
    void onMoveLeft();
    void onMoveRight();
    void onMoveLeftAndUp();
    void onMoveRightAndUp();

    void onTouchUp();

    @Override
    default String getAction() {
        return "MoveListener";
    }
}
