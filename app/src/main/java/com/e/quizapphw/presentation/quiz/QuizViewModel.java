package com.e.quizapphw.presentation.quiz;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.e.quizapphw.App;
import com.e.quizapphw.core.SingleLiveEvent;
import com.e.quizapphw.data.remote.IQuizApiClient;
import com.e.quizapphw.model.Question;
import com.e.quizapphw.model.QuizResult;
import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {

    private IQuizApiClient quizApiClient = App.quizApiClient;
    private List<Question> mQuestions;

    MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    //MutableLiveData<Boolean> isLoading = new MutableLiveData<>(); //TODO:

    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();

    private Integer count;

    public QuizViewModel() {
        currentQuestionPosition.setValue(1);
        count = 1;
    }

    void init(int amount, Integer category, String difficulty) {
        //currentQuestionPosition.setValue(0); //TODO:
        //isLoading.setValue(true); //TODO:

        quizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                //isLoading.setValue(false); //TODO:
                questions.postValue(result);
                mQuestions = result;
                questions.setValue(mQuestions);
            }

            @Override
            public void onFailure(Exception e) {
                //
                //isLoading.setValue(false); //TODO:
                //Log.d("ololo", "Error@" + e.getMessage()); //TODO:
            }
        });

        finishEvent.call();
    }

    private int getCorrectAnswersAmount() {
        //TODO:
        return 0;
    }

    void finishQuiz() {
        QuizResult result = new QuizResult(
                0,
                "",
                "",
                mQuestions,
                getCorrectAnswersAmount(),
                new Date()
        );

        int resultId = App.historyStorage.saveQuizResult(result);

        //TODO: Start Result activity
        finishEvent.call();
        openResultEvent.setValue(resultId);
    }

    void onBackPressed() {
        currentQuestionPosition.setValue(--count);
    }

    void onSkipClick() {
        currentQuestionPosition.setValue(++count);
    }

    void onAnswerClick(int position, int selectedAnswerPosition) {
        // 20, 19
        // 20, 20
        // 20, 21
        // 20, -1

        if (mQuestions.size() > position && position >= 0) {
            mQuestions.get(position).setSelectedAnswerPosition(selectedAnswerPosition);
            questions.setValue(mQuestions);

            // 20, 17 -> 18
            // 20, 18 -> 19
            // 20, 19 -> 20
            // 20, 20

            if (position + 1 == mQuestions.size()) {
                //TODO: Finish quiz
            } else {
                currentQuestionPosition.setValue(position + 1);
            }
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
