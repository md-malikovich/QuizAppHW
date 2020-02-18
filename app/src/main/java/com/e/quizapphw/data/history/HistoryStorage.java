package com.e.quizapphw.data.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.e.quizapphw.model.History;
import com.e.quizapphw.model.QuizResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HistoryStorage implements IHistoryStorage { //логика работы с room

    public HistoryDao dao;
    //private Executor executor = Executors.newSingleThreadExecutor();

    public HistoryStorage (HistoryDao historyDao) {
        dao = historyDao;
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return dao.get(id);
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return (int) dao.insert(quizResult);
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return dao.getAll();
    }

    @Override
    public LiveData<List<History>> getAllHistory() {
        return Transformations.map(getAll(), quizResults -> {
            ArrayList<History> histories = new ArrayList<>();

            if (quizResults.size() > 0) { //TODO:!!!
                for (int i = 0; i < quizResults.size(); i++) {
                    histories.add(i, new History(
                            quizResults.get(i).getCategory(),
                            quizResults.get(i).getDifficulty(),
                            quizResults.get(i).getCorrectAnswersAmount(),
                            quizResults.get(i).getCreatedAt(),
                            quizResults.get(i).getQuestions().size(),
                            quizResults.get(i).getId()));
                }
            }
            return histories;
        });
    }

    @Override
    public void delete(QuizResult quizResult) {
        dao.delete(quizResult);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }
}