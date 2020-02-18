package com.e.quizapphw.presentation.quiz;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.e.quizapphw.App;
import com.e.quizapphw.core.SingleLiveEvent;
import com.e.quizapphw.data.remote.IQuizApiClient;
import com.e.quizapphw.model.Question;
import com.e.quizapphw.model.QuizResult;
import com.e.quizapphw.presentation.result.ResultActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {

    private IQuizApiClient quizApiClient = App.quizApiClient;
    private List<Question> mQuestions;
    private String mCategoryString;
    private String mDifficultyString;

    MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    //MutableLiveData<Boolean> isLoading = new MutableLiveData<>(); //TODO:

    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();

    private Integer count;
    private int id = 0;

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

                try {
                    if (mQuestions.get(1).getCategory().equals(mQuestions.get(2).getCategory())) {
                        mCategoryString = mQuestions.get(1).getCategory();
                    } else {
                        mCategoryString = "Mixed";
                    }
                    if (difficulty != null) {
                        mDifficultyString = mQuestions.get(0).getDifficulty().toString();
                    } else {
                        mDifficultyString = "All";
                    }
                } catch (IndexOutOfBoundsException e) {
                    //isLoading.setValue(false);
                    finishEvent.call();
                }
            }

            @Override
            public void onFailure(Exception e) {
                //isLoading.setValue(false); //TODO:
                //Log.d("ololo", "Error@" + e.getMessage()); //TODO:
            }
        });

        finishEvent.call();
    }

    private int getCorrectAnswersAmount() {
        int correctAnswerAmounts = 0;
        for (int i = 0; i <= mQuestions.size() - 1; i++) {
            if (mQuestions.get(i).getSelectedAnswerPosition() != -1) {
                String correctAnswer = mQuestions.get(i).getCorrectAnswer();
                String selectedAnswer = mQuestions.get(i).getAnswers()
                        .get(mQuestions.get(i).getSelectedAnswerPosition());
                if (correctAnswer.equals(selectedAnswer)) {
                    ++correctAnswerAmounts;
                }
            }
        }
        return correctAnswerAmounts;
    }

    void finishQuiz() {
        QuizResult result = new QuizResult(
                id,
                mCategoryString,
                mDifficultyString,
                mQuestions,
                getCorrectAnswersAmount(),
                new Date()
        );
        Log.e("ololo", "finishQuiz: id:"+id +" category:"+mCategoryString +" difficulty:"+mDifficultyString +" date:"+new Date());

        int resultId = App.historyStorage.saveQuizResult(result);

        //ResultActivity.start(); //TODO:!!!
        finishEvent.call();
        openResultEvent.setValue(resultId);

//        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("d.MMM.yyyy, HH:mm");
//        String date = df.format(Calendar.getInstance().getTime());
//        date
    }

    void onBackPressed() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            if (currentPosition != 0) {
                currentQuestionPosition.setValue(--count);
            } else {
                finishEvent.call();
            }
        } else {
            finishEvent.call();
        }
    }

    void onSkipClick() {
        //currentQuestionPosition.setValue(++count);
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            onAnswerClick(currentQuestionPosition.getValue(), -1);
        } else {
            finishQuiz();
        }
    }

    void onAnswerClick(int position, int selectedAnswerPosition) {

//        if (mQuestions.size() > position && position >= 0) {
//            mQuestions.get(position).setSelectedAnswerPosition(selectedAnswerPosition);
//            questions.setValue(mQuestions);
//            Log.e("ololo", "onAnswerClick setAnswer: " + position + selectedAnswerPosition);
//
//            questions.setValue(mQuestions);
//
//            if (position + 1 == mQuestions.size()) {
//                finishQuiz();
//            } else {
//                currentQuestionPosition.setValue(++count); //TODO: ++count
//            }
//        }

        if (mQuestions.size() > position && position >= 0) {
            if (mQuestions.get(position).getSelectedAnswerPosition() == null) {
                mQuestions.get(position).setSelectedAnswerPosition(selectedAnswerPosition);
                questions.setValue(mQuestions);
            }
            if (position + 1 == mQuestions.size()) {
                finishQuiz();
            } else {
                currentQuestionPosition.setValue(++count);
            }
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
