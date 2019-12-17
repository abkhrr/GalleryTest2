package com.adyabukhari.pixalist.Model;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class PixabayViewModels extends BaseObservable {

    private PixabayImage pixabayImage;

    public PixabayViewModels(PixabayImage pixabayImage) {
        this.pixabayImage = pixabayImage;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}
