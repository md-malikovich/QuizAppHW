package com.e.quizapphw.data.history;

import androidx.lifecycle.LiveData;

import com.e.quizapphw.model.History;
import com.e.quizapphw.model.QuizResult;

import java.util.List;

public interface IHistoryStorage {
    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    LiveData<List<QuizResult>> getAll();

    LiveData<List<History>> getAllHistory();

    void delete(QuizResult quizResult);

    void deleteAll();

    void deleteById(int id);
}
