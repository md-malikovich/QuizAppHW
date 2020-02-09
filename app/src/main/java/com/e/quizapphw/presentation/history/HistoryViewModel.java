package com.e.quizapphw.presentation.history;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.e.quizapphw.model.History;
import java.util.List;

public class HistoryViewModel extends ViewModel {

    private List<History> mHistory;

    void onHistoryClick(int position) {
        mHistory.get(position);
    }

    public MutableLiveData<String> history = new MutableLiveData<>();

    public HistoryViewModel() {
        history.setValue("First");
    }
}
