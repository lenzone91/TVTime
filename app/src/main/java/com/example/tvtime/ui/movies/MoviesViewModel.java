package com.example.tvtime.ui.movies;

import android.database.Observable;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.example.tvtime.network.APIClient;
import com.example.tvtime.network.APIInterface;

import org.json.JSONArray;

import java.net.URL;

public class MoviesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MoviesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is movies fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}