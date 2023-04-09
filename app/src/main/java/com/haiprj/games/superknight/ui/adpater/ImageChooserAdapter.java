package com.haiprj.games.superknight.ui.adpater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.haiprj.games.superknight.R;
import com.haiprj.games.superknight.databinding.ItemImageChooserBinding;
import com.haiprj.games.superknight.models.ImageItemModel;

import java.util.ArrayList;
import java.util.List;

public class ImageChooserAdapter extends RecyclerView.Adapter<ImageChooserAdapter.ImageHolder> {

    private final Context context;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private final List<ImageItemModel> list = new ArrayList<>();
    public ImageChooserAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void update(List<ImageItemModel> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageHolder(LayoutInflater.from(this.context).inflate(R.layout.item_image_chooser, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        holder.load(list.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder{
        ItemImageChooserBinding binding;
        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        @SuppressLint({"DefaultLocale", "NotifyDataSetChanged"})
        public void load(ImageItemModel model){
            binding.imgItem.setImageResource(model.getResId());
            binding.numberImage.setText(String.format("%d", model.getImageNumber()));
            binding.layoutItem.setSelected(model.isChoose());
            binding.getRoot().setOnClickListener(v -> {
                onItemClickListener.onClick("Click", model);
                list.forEach(m -> {
                    m.setChoose(false);
                });
                list.get(getPosition()).setChoose(true);
                notifyDataSetChanged();
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(String action, Object... objects);
    }
}
