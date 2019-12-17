package com.adyabukhari.pixalist;

import android.widget.ImageView;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.adyabukhari.pixalist.Model.PixabayImage;
import com.bumptech.glide.Glide;

/**
 * Created by Abkhrr on 17/12/2019.
 *
 * Email: bukhariadbuk@gmail.com
 * GitHub: https://github.com/abkhrr
 *
 */

public class PixabayViewModels extends BaseObservable {

    private PixabayImage pixabayImage;

    public PixabayViewModels(PixabayImage pixabayImage) {
        this.pixabayImage = pixabayImage;
    }

    // Getting Image URL (models)
    public String getImageUrl() {
        return pixabayImage.getPreviewURL();
    }

    // Binding Image From Layout

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}
