package com.e.quizapphw.data.remote;

import com.e.quizapphw.core.IBaseCallback;
import com.e.quizapphw.model.Question;
import com.e.quizapphw.model.TriviaCategory;

import java.util.List;

public interface IQuizApiClient {

    void getQuestions(int amount, Integer category, String difficulty, QuestionsCallback callback);

    void getTriviaCategories(TriviaCategoriesCallback triviaCategoriesCallback);

    void getCountGlobal(CountGlobalCallback callback);

    void getQuestionCount(Integer category, QuestionCountCallback questionCount);

    interface QuestionsCallback extends IBaseCallback<List<Question>> { // братишка - generic
        void onSuccess (List<Question> questions);
        void onFailure(Exception e);
    }

    interface TriviaCategoriesCallback {
        void onSuccess (List<TriviaCategory> triviaCategories);
        void onFailure(Exception e);
    }

    interface CountGlobalCallback extends IBaseCallback<QuizGlobalResponse> {
        //
    }

    interface QuestionCountCallback extends IBaseCallback<QuizQuestionCount> {
        //
    }

}

