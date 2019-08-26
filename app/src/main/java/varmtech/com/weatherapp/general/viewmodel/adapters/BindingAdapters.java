package varmtech.com.weatherapp.general.viewmodel.adapters;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import varmtech.com.weatherapp.constants.AppConstants;

public class BindingAdapters {
    @BindingAdapter("urlImage")
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(AppConstants.IMAGE_PATH + url + AppConstants.IMAGE_EXTENSION).into(view);
        //Picasso.get().load(url).error(errorImage).into(view);
    }
}
