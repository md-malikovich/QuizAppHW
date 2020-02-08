package com.e.quizapphw.data.remote;

import com.e.quizapphw.model.Question;
import com.e.quizapphw.model.TriviaCategory;

import java.util.List;

public interface IQuizApiClient {

    void getQuestions(QuestionsCallback callback);

    //void getTriviaCategories(TriviaCategoriesCallback triviaCategoriesCallback);

    interface QuestionsCallback { // братишка
//    interface QuestionsCallback extends IBaseCallback<List<Question>> { // братишка - generic
        void onSuccess (List<Question> questions);
        void onFailure(Exception e);
    }

//    public interface TriviaCategoriesCallback {
//        void onSuccess (List<TriviaCategory> triviaCategories);
//        void onFailure(Exception e);
//    }
}

