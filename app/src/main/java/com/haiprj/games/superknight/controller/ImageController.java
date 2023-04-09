package com.haiprj.games.superknight.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.haiprj.games.superknight.models.ImageItemModel;
import com.haiprj.games.superknight.models.ImageModel;
import com.haiprj.games.superknight.ui.widget.CreateMapSurface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ImageController {

    private final Context context;
    private final CreateMapSurface surface;
    private int col, row;
    private ImageModel[][] imageModels;
    public ImageController(Context context, CreateMapSurface surface) {
        this.context = context;
        this.surface = surface;
    }

    public void setMatrix(int col, int row) {
        this.col = col;
        this.row = row;
        this.imageModels = new ImageModel[col][row];
        int x = 0;
        int y = 0;
        while (x < col && y < row) {
            imageModels[x][y] = new ImageModel(
                    this.context,
                    "0",
                    64,
                    64,
                    x * 64,
                    y * 64,
                    (int[]) null
            );
            x++;
            if (x >= col) {
                y++;
                x = 0;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    public void update(ImageItemModel model) {
        ImageModel imageModel = null;
        int x = 0;
        int y = 0;
        while (x < col && y < row) {
            if (imageModels[x][y].isChoose()){
                imageModel = imageModels[x][y];
                break;
            }
            x++;
            if (x >= col) {
                y++;
                x = 0;
            }
        }
        if (imageModel == null) return;
        imageModel.setName(String.format("%d", model.getImageNumber()));
        imageModel.setImageId(model.getResId());
        imageModel.setSurface(this.surface);
        imageModel.update();
    }

    public void draw(Canvas canvas) {
        int x = 0;
        int y = 0;
        while (x < col && y < row) {
            if (imageModels[x][y] != null) {
                imageModels[x][y].draw(canvas);
            }
            x++;
            if (x >= col) {
                y++;
                x = 0;
            }
        }
    }

    public void onTouchDown(MotionEvent event) {
        int x = 0;
        int y = 0;
        while (x < col && y < row) {
            if (!imageModels[x][y].touchDown(event)) {
                imageModels[x][y].setChoose(false);
            }
            x++;
            if (x >= col) {
                y++;
                x = 0;
            }
        }
    }

    public void writeFileTo(File file) {
        int x = 0;
        int y = 0;
        StringBuilder data = new StringBuilder();
        while (x < col && y < row) {
            data.append(imageModels[x][y].getName()).append("_");
            x++;
            if (x >= col) {
                y++;
                x = 0;
                data.append("\n");
            }
        }
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(data.toString());
            writer.close();
            System.out.println("Data written to file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
