package com.e.quizapphw.data.history;

import androidx.lifecycle.LiveData;

import com.e.quizapphw.model.QuizResult;

import java.util.List;

public interface IHistoryStorage {
    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    LiveData<List<QuizResult>> getAll();

    void delete(int id);

    void deleteAll();
}
