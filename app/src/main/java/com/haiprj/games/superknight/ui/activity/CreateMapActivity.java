package com.haiprj.games.superknight.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.haiprj.games.superknight.R;
import com.haiprj.games.superknight.base.view.BaseActivity;
import com.haiprj.games.superknight.databinding.ActivityCreateMapBinding;
import com.haiprj.games.superknight.models.ImageItemModel;
import com.haiprj.games.superknight.ui.adpater.ImageChooserAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateMapActivity extends BaseActivity<ActivityCreateMapBinding> {
    ImageChooserAdapter adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, CreateMapActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void initView() {
        binding.createMap.startThread();
        adapter = new ImageChooserAdapter(this);

        binding.rcvImage.setAdapter(adapter);
        adapter.update(getListImage());
    }

    @Override
    protected void addEvent() {
        adapter.setOnItemClickListener(new ImageChooserAdapter.OnItemClickListener() {
            @Override
            public void onClick(String action, Object... objects) {
                ImageItemModel model = (ImageItemModel) objects[0];
                binding.createMap.setCurrentModel(model);
            }
        });
        binding.btnSave.setOnClickListener(v -> {
            binding.createMap.writeFileTo(new File(""));
        });
    }

    private List<ImageItemModel> getListImage() {
        List<ImageItemModel> models = new ArrayList<>();
        models.add(new ImageItemModel(R.drawable.wall, 1));
        models.add(new ImageItemModel(R.drawable.wall_up, 2));
        return models;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_map;
    }
}
