package com.example.tvtime.ui.series;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SeriesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SeriesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is series fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}