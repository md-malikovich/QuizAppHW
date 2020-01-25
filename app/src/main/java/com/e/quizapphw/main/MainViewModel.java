package com.e.quizapphw.main;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> message = new MutableLiveData<>();

    public MainViewModel() {
        Log.d("ololo", "View model created");
        message.setValue("First");
    }
}
