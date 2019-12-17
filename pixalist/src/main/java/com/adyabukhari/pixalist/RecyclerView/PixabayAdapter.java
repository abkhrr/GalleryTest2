package com.adyabukhari.pixalist.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adyabukhari.pixalist.Model.PixabayImage;
import com.adyabukhari.pixalist.PixabayViewModels;
import com.adyabukhari.pixalist.R;
import com.adyabukhari.pixalist.databinding.PixabayItemBinding;

import java.util.List;

public class PixabayAdapter extends RecyclerView.Adapter<PixabayAdapter.PixabayImageViewHolder> {

    private List<PixabayImage> pixabayImageList;
    private Context context;
    private RecyclerView recyclerView;

    public PixabayAdapter(List<PixabayImage> pixabayImageList, Context context, RecyclerView recyclerView) {
        this.pixabayImageList = pixabayImageList;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public PixabayImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new PixabayAdapter.PixabayImageViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.pixabay_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PixabayImageViewHolder holder, int position) {

        holder.pixabayImageItemBinding.setViewmodel(new PixabayViewModels(pixabayImageList.get(position)));
    }

    @Override
    public int getItemCount() {
        return pixabayImageList.size();
    }

    public static class PixabayImageViewHolder extends RecyclerView.ViewHolder {

        public final PixabayItemBinding pixabayImageItemBinding;

        public PixabayImageViewHolder(@NonNull View v) {
            super(v);
            pixabayImageItemBinding = PixabayItemBinding.bind(v);
        }
    }
}
