package com.e.quizapphw.presentation.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e.quizapphw.App;
import com.e.quizapphw.model.History;
import java.util.List;

public class HistoryViewModel extends ViewModel {

    private List<History> mHistory;
    public MutableLiveData<String> history = new MutableLiveData<>();

    public LiveData<List<History>> history1 = App.historyStorage.getAllHistory();

    void onHistoryClick(int position) {
        mHistory.get(position);
    }

    public HistoryViewModel() {
        history.setValue("First");
    }
}
//TODO:!!!