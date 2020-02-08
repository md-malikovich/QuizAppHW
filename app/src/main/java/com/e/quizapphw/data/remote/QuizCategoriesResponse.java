package com.e.quizapphw.data.remote;

import java.util.List;
import com.e.quizapphw.model.TriviaCategory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizCategoriesResponse {

    @SerializedName("trivia_categories")
    @Expose
    private List<TriviaCategory> triviaCategories;

        public List<TriviaCategory> getTriviaCategories() {
        return triviaCategories;
    }

    public void setTriviaCategories(List<TriviaCategory> triviaCategories) {
        this.triviaCategories = triviaCategories;
    }
}
