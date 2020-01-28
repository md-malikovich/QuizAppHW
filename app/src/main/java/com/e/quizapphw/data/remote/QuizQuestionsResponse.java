package com.e.quizapphw.data.remote;

import com.e.quizapphw.model.Question;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class QuizQuestionsResponse { // контейнер для вопросов - моделька

    @SerializedName("response_code")
    private int responseCode;
    private List<Question> results; // массив из Вопросов

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<Question> getResults() {
        return results;
    }

    public void setResults(List<Question> results) {
        this.results = results;
    }
}
