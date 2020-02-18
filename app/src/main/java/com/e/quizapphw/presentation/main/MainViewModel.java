package com.e.quizapphw.presentation.main;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e.quizapphw.App;
import com.e.quizapphw.core.SingleLiveEvent;
import com.e.quizapphw.data.remote.IQuizApiClient;
import com.e.quizapphw.data.remote.QuizGlobalResponse;
import com.e.quizapphw.data.remote.QuizQuestionCount;
import com.e.quizapphw.model.TriviaCategory;

import java.util.List;

public class MainViewModel extends ViewModel {

    public MutableLiveData<List<TriviaCategory>> categoriesLiveData = new MutableLiveData<>();
    public MutableLiveData<QuizGlobalResponse> globalLiveData = new MutableLiveData<>();
    public MutableLiveData<QuizQuestionCount> questionCountLiveData = new MutableLiveData<>();

    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<String> messageEvent = new SingleLiveEvent<>();

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    void callFinish() {
        finishEvent.call();
    }

    void onShowMessageClick() {
        messageEvent.setValue("Hello!");
    }

    public void getGlobal() {
        App.quizApiClient.getCountGlobal(new IQuizApiClient.CountGlobalCallback() {
            @Override
            public void onSuccess(QuizGlobalResponse result) {
                globalLiveData.setValue(result);
                Log.e("ololo", "Global " + result.getCategories() + result.getGlobal());
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("TAG", "onFailure: countGlobal " + e);
            }
        });
    }

    public void getCategories() {
        App.quizApiClient.getTriviaCategories(new IQuizApiClient.TriviaCategoriesCallback() {
            @Override
            public void onSuccess(List<TriviaCategory> result) {
                categoriesLiveData.setValue(result);
                Log.e("ololo", "Categories: " + result.get(0).getName() + result.get(0).getId());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void getQuestionsCount(Integer integer) {
        App.quizApiClient.getQuestionCount(integer, new IQuizApiClient.QuestionCountCallback() {
            @Override
            public void onSuccess(QuizQuestionCount result) {
                questionCountLiveData.setValue(result);
                Log.e("ololo", "QuestionCount: " + result.toString());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
