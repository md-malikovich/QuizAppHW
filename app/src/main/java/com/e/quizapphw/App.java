package com.e.quizapphw;

import android.app.Application;

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

    @Override
    public void onCreate() {
        super.onCreate();

        //quizApiClient = new QuizApiClient();
        //historyStorage = new HistoryStorage();

        quizDatabase = Room.databaseBuilder(
                this,
                QuizDatabase.class,
                "quiz.db").fallbackToDestructiveMigration()
                .build();

        QuizRepository repository = new QuizRepository(
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
