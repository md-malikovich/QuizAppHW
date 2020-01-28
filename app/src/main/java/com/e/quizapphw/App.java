package com.e.quizapphw;

import android.app.Application;
import com.e.quizapphw.data.remote.IQuizApiClient;
import com.e.quizapphw.data.remote.QuizApiClient;

public class App extends Application {

    public static IQuizApiClient quizApiClient;

//    public static void quizApiClient(IQuizApiClient.QuestionsCallback callback) {
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        quizApiClient = new QuizApiClient();
    }
}
