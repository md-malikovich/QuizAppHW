package com.e.quizapphw.presentation.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.e.quizapphw.App;
import com.e.quizapphw.model.QuizResult;

public class ResultViewModel extends ViewModel {

    MutableLiveData<QuizResult> quizResult = new MutableLiveData<>();

    public void getResult(Integer id) {
        quizResult.setValue(App.quizDatabase.historyDao().get(id));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
