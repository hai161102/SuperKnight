package com.haiprj.games.superknight.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

import com.haiprj.games.superknight.R;
import com.haiprj.games.superknight.actions.MoveAction;
import com.haiprj.games.superknight.actions.interfaces.MoveListener;
import com.haiprj.games.superknight.base.widget.BaseGameSurface;
import com.haiprj.games.superknight.controller.MoveActionController;
import com.haiprj.games.superknight.controller.PlayerController;
import com.haiprj.games.superknight.controller.StaticModelController;
import com.haiprj.games.superknight.models.StaticModel;

public class GameSurface extends BaseGameSurface {

    private PlayerController playerController;
    private MoveActionController moveActionController;

    private StaticModelController staticModelController;

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
        initMoveAction();
        initPlayer();
        initStaticModel();
    }

    private void initStaticModel() {
        staticModelController = new StaticModelController(getContext(), getWidth(), getHeight(), playerController);
//        staticModelController.setStaticModel(new StaticModel(
//                getContext(),
//                "Up Entity",
//                64,
//                64,
//
//        ));
    }

    private void initMoveAction() {
        moveActionController = new MoveActionController();
        moveActionController.setMoveAction(new MoveAction(
                getContext(),
                180f,
                getHeight() - 260f,
                240,
                240,
                R.drawable.move_up,
                R.drawable.move_left,
                R.drawable.move_right
        ));
        moveActionController.getMoveAction().setListener(new MoveListener() {
            @Override
            public void onMoveUp() {
                playerController.getPlayer().jump();
            }

            @Override
            public void onMoveLeft() {
                playerController.getPlayer().left();
            }

            @Override
            public void onMoveRight() {
                playerController.getPlayer().right();
            }

            @Override
            public void onMoveLeftAndUp() {
            }

            @Override
            public void onMoveRightAndUp() {
            }

            @Override
            public void onTouchUp() {
                playerController.getPlayer().clearFocus();
            }
        });
    }

    private void initPlayer() {
        playerController = new PlayerController();
        playerController.setPlayer(
                getContext(),
                "Super Herro",
                64,
                64,
                this.getWidth() / 2f - 64f,
                this.getHeight() / 4f,
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
        playerController.getPlayer().setWorldRect(0, 0, 1000, 1000);
//        playerController.getPlayer().setupCameraRect(
//                this.getWidth() / 4f,
//                this.getHeight() / 4f,
//                this.getWidth() - this.getWidth() / 4f,
//                this.getHeight() - this.getHeight() / 4f);
        playerController.getPlayer().setPlayerSpeed(10f);
    }

    @Override
    protected void update() {
        if (playerController != null)
            playerController.update();
        if (staticModelController != null)
            staticModelController.update();
        if (moveActionController != null)
            moveActionController.update();
    }

    @Override
    protected void gameDraw(Canvas canvas) {
        if (staticModelController != null)
            staticModelController.draw(canvas);

        if (playerController != null)
            playerController.draw(canvas);
        if (moveActionController != null)
            moveActionController.draw(canvas);
    }

    @Override
    protected void onTouchDown(View view, MotionEvent event) {
        super.onTouchDown(view, event);
//        if (event.getX() < this.getWidth() / 2f) {
//            playerController.getPlayer().left();
//        }
//        else {
//            playerController.getPlayer().right();
//        }
        if (moveActionController != null)
            moveActionController.getMoveAction().moveTouchDown(event);
    }

    @Override
    protected void onTouchUp(View view, MotionEvent event) {
        super.onTouchUp(view, event);
//        playerController.getPlayer().clearFocus();
        if (moveActionController != null)
            moveActionController.getMoveAction().moveTouchUp(event);
    }

    @Override
    protected void onTouchMove(View view, MotionEvent event) {
        super.onTouchMove(view, event);
        if (moveActionController != null)
            moveActionController.getMoveAction().move(event);
    }
}
