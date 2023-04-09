package com.haiprj.games.superknight.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.haiprj.games.superknight.R;
import com.haiprj.games.superknight.base.view.BaseActivity;
import com.haiprj.games.superknight.databinding.ActivityGameBinding;

public class GameActivity extends BaseActivity<ActivityGameBinding> {
    
    public static void start(Context context) {
        Intent starter = new Intent(context, GameActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void initView() {
        binding.gameSurface.startThread();
    }

    @Override
    protected void addEvent() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_game;
    }
}
