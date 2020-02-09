package com.e.quizapphw;

import android.app.Application;
import com.e.quizapphw.data.QuizRepository;
import com.e.quizapphw.data.history.HistoryStorage;
import com.e.quizapphw.data.history.IHistoryStorage;
import com.e.quizapphw.data.remote.IQuizApiClient;
import com.e.quizapphw.data.remote.QuizApiClient;

public class App extends Application {

    public static IQuizApiClient quizApiClient;
    public static IHistoryStorage historyStorage;

    @Override
    public void onCreate() {
        super.onCreate();

        //quizApiClient = new QuizApiClient();
        //historyStorage = new HistoryStorage();

        QuizRepository repository = new QuizRepository(
                //quizApiClient,
                //historyStorage
                new QuizApiClient(),
                new HistoryStorage()
        );

        quizApiClient = repository;
        historyStorage = repository;
    }
}
