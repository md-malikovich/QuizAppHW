package com.e.quizapphw.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import com.e.quizapphw.data.history.IHistoryStorage;
import com.e.quizapphw.data.remote.IQuizApiClient;
import com.e.quizapphw.model.Question;
import com.e.quizapphw.model.QuizResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class QuizRepository implements IHistoryStorage, IQuizApiClient {

    private IQuizApiClient quizApiClient;
    private IHistoryStorage historyStorage;

    public QuizRepository(
            IQuizApiClient client,
            IHistoryStorage storage
    ) {
        quizApiClient = client;
        historyStorage = storage;
    }

    private Question shuffleAnswers(Question question) {
        ArrayList<String> answers = new ArrayList<>();

        answers.add(question.getCorrectAnswer());
        answers.addAll(question.getIncorrectAnswers());

        Collections.shuffle(answers);
        question.setAnswers(answers);
        Log.e("ololo", "shuffleAns: " + answers);
        return question;
    }

    @Override
    public void getQuestions(int amount, Integer category, String difficulty, final QuestionsCallback callback) {
        quizApiClient.getQuestions(amount, category, difficulty, new QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                for (int i = 0; i < result.size(); i++) {
                    result.set(i, shuffleAnswers(result.get(i)));
                }
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return 0;
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return null;
    }

    @Override
    public void deleteById(int id) {
        historyStorage.deleteById(id);
    }

    @Override
    public void deleteAll() {
        historyStorage.deleteAll();
    }

    @Override
    public void delete(QuizResult quizResult) {
        historyStorage.delete(quizResult);
    }

    @Override
    public void getTriviaCategories(TriviaCategoriesCallback triviaCategoriesCallback) {
        //
    }

    @Override
    public void getCountGlobal(CountGlobalCallback callback) {
        //
    }

    @Override
    public void getQuestionCount(Integer category, QuestionCountCallback questionCount) {
        //
    }
}

