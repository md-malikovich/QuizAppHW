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

    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();


    void init(int amount, Integer category, String difficulty) {
        quizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                questions.postValue(result);
                mQuestions = result;
                questions.setValue(mQuestions);
                Log.e("ololo", "onSuccess: " + result.get(0).getAnswers());
                Log.e("ololo", result.get(0).getAnswers() + "");
            }

            @Override
            public void onFailure(Exception e) {

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
        //TODO:
    }

    void onSkipClick() {
        //TODO:
    }

    void onAnswerClick(int position, int selectedAnswerPosition) {
        // 20, 19
        // 20, 20
        // 20, 21
        // 20, -1

        if (mQuestions.size() > position && position >= 0) {
            mQuestions.get(position)
                    .setSelectedAnswerPosition(selectedAnswerPosition);

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
}