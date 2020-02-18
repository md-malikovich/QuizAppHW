package com.e.quizapphw;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.e.quizapphw.data.QuizRepository;
import com.e.quizapphw.data.db.QuizDatabase;
import com.e.quizapphw.data.history.HistoryStorage;
import com.e.quizapphw.data.history.IHistoryStorage;
import com.e.quizapphw.data.remote.IQuizApiClient;
import com.e.quizapphw.data.remote.QuizApiClient;
import com.e.quizapphw.model.History;

import java.util.List;

public class App extends Application {

    public static IQuizApiClient quizApiClient;
    public static IHistoryStorage historyStorage;
    public static QuizDatabase quizDatabase;
    public static QuizRepository repository;
    //public static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        //instance = this;

        //quizApiClient = new QuizApiClient();
        //historyStorage = new HistoryStorage();

        quizDatabase = Room.databaseBuilder(
                this,
                QuizDatabase.class,
                "quiz.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        repository = new QuizRepository(
                new QuizApiClient(),
                new HistoryStorage(quizDatabase.historyDao())
        ) {
            @Override
            public LiveData<List<History>> getAllHistory() {
                return null;
            }
        };

        quizDatabase.historyDao();

        repository = new QuizRepository(
                //quizApiClient,
                //historyStorage
                new QuizApiClient(),
                new HistoryStorage(quizDatabase.historyDao())
        ) {
            @Override
            public LiveData<List<History>> getAllHistory() {
                return null;
            }
        };

        quizApiClient = repository;
        historyStorage = repository;
    }
}
