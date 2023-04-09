package com.haiprj.games.superknight.ui.activity;

import com.haiprj.games.superknight.R;
import com.haiprj.games.superknight.base.view.BaseActivity;
import com.haiprj.games.superknight.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected void initView() {
    }

    @Override
    protected void addEvent() {
        binding.startGame.setOnClickListener(v -> {
            GameActivity.start(this);
        });
        binding.createMap.setOnClickListener(v -> CreateMapActivity.start(this));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}