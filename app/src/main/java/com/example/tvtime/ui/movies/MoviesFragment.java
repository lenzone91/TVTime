package com.example.tvtime.ui.movies;

import android.database.Observable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.tvtime.databinding.FragmentMoviesBinding;
import com.example.tvtime.network.APIClient;
import com.example.tvtime.network.APIInterface;

import org.json.JSONArray;

public class MoviesFragment extends Fragment {

    private FragmentMoviesBinding binding;
    private APIInterface api;

    @BindingAdapter("app:imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        if (url == null) {
            imageView.setImageDrawable(null);
        } else {
            Glide.with(imageView.getContext()).load("https://images-na.ssl-images-amazon.com/images/M/MV5BMTYwNjAyODIyMF5BMl5BanBnXkFtZTYwNDMwMDk2._V1_.jpg").into(imageView); // replace with your fav image loading lib
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MoviesViewModel moviesViewModel =
                new ViewModelProvider(this).get(MoviesViewModel.class);

        binding = FragmentMoviesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        api = APIClient.getClient().create(APIInterface.class);
        getMovies();

        return root;
    }

    private void getMovies(){
        Observable<JSONArray> observable = api.searchMovies("batman");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}