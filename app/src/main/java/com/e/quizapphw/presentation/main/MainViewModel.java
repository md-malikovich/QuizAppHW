package com.e.quizapphw.presentation.main;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e.quizapphw.core.SingleLiveEvent;

public class MainViewModel extends ViewModel {

    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<String> messageEvent = new SingleLiveEvent<>();

    void callFinish() {
        finishEvent.call();
    }

    void onShowMessageClick() {
        messageEvent.setValue("Hello!");
    }
}
