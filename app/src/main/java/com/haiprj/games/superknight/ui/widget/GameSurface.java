package com.haiprj.games.superknight.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

import com.haiprj.games.superknight.R;
import com.haiprj.games.superknight.base.widget.BaseGameSurface;
import com.haiprj.games.superknight.controller.PlayerController;

public class GameSurface extends BaseGameSurface {

    private PlayerController playerController;

    public GameSurface(Context context) {
        super(context);
    }

    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameSurface(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void initView(SurfaceHolder surfaceHolder) {
        initPlayer();
    }

    private void initPlayer() {
        playerController = new PlayerController();
        playerController.setPlayer(
                getContext(),
                "Super Herro",
                128,
                128,
                0,
                0,
                R.drawable.knight_animations_1,
                R.drawable.knight_animations_2,
                R.drawable.knight_animations_3,
                R.drawable.knight_animations_4,
                R.drawable.knight_animations_5,
                R.drawable.knight_animations_6,
                R.drawable.knight_animations_7,
                R.drawable.knight_move_vertical_1,
                R.drawable.knight_move_vertical_2,
                R.drawable.knight_move_vertical_3,
                R.drawable.knight_move_vertical_4,
                R.drawable.knight_move_vertical_5,
                R.drawable.knight_move_vertical_6
        );
    }

    @Override
    protected void update() {

    }

    @Override
    protected void gameDraw(Canvas canvas) {
        playerController.draw(canvas);
    }
}
