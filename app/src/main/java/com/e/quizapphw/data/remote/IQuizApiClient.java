package com.e.quizapphw.data.remote;

import com.e.quizapphw.model.Question;
import java.util.List;

public interface IQuizApiClient {

    void getQuestions(QuestionsCallback callback);

    public interface QuestionsCallback { // братишка
        void onSuccess (List<Question> questions);
        void onFailure(Exception e);
    }

//    public interface CategoriesCallback { // братишка
//        void onSuccess (List<Question> questions);
//        void onFailure(Exception e);
//    }
}
