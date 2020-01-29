package com.e.quizapphw.data.remote;

import android.util.Log;

import com.e.quizapphw.core.CoreCallback;
import com.e.quizapphw.model.TriviaCategory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiClient implements IQuizApiClient{

    private ArrayList<String> categories;

    private Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    private QuizApi client = retrofit.create(QuizApi.class);
    private QuizApiTriviaCategories client2 = retrofit.create(QuizApiTriviaCategories.class);

    @Override
    public void getQuestions(final QuestionsCallback callback) {
        Call<QuizQuestionsResponse> call = client.getQuestions(
                20,
                null,
                "easy"
        );
        Log.d("ololo", "URL - " + call.request().url());

        call.enqueue(new CoreCallback<QuizQuestionsResponse>() {
            @Override
            public void onSuccess(QuizQuestionsResponse result) {
                callback.onSuccess(result.getResults());
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }

    @Override
        public void getTriviaCategories(final TriviaCategoriesCallback triviaCategoriesCallback) {
            Call<TriviaCategoriesCallback> call2 = client2.getTriviaCategories("General Knowledge");
            Log.d("ololo", "URL - " + call2.request().url());
            categories = new ArrayList<>();

            call2.enqueue(new CoreCallback<QuizCategoriesResponse>() {
                @Override
                public void onSuccess(QuizCategoriesResponse result) {
                    triviaCategoriesCallback.onSuccess(result.getTriviaCategories()); //TODO: !!!!!!!!!!!!!!!!!!!
                }

                @Override
                public void onFailure(Exception e) {
                    //
                }
            });

//        call2.enqueue(new Callback<QuizCategoriesResponse>() {
//            @Override
//            public void onResponse(Call<QuizCategoriesResponse> call, Response<QuizCategoriesResponse> response) {
//                if(response.isSuccessful() && response.body() != null) {
//                    triviaCategoriesCallback.onSuccess(response.body().getTriviaCategories().get(0).getId());
//                    //Log.d("ololo" + getTriviaCategories("Entertainment"));
//                } else {
//                    //
//                }
//            }
//
//            @Override
//            public void onFailure(Call<QuizCategoriesResponse> call, Throwable t) {
//                triviaCategoriesCallback.onFailure(new Exception(t));
//            }
//        });

//        call.enqueue(new Callback<QuizQuestionsResponse>() {
//            @Override
//            public void onResponse(Call<QuizQuestionsResponse> call, Response<QuizQuestionsResponse> response) {
//                if(response.isSuccessful()) {
//                    if ((response.body() != null)) {
//                        callback.onSuccess(response.body().getResults());
//                    } else {
//                        callback.onFailure(new Exception("Response body is empty" + response.code()));
//                    }
//                } else {
//                    callback.onFailure(new Exception("Request failed " + response.code()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<QuizQuestionsResponse> call, Throwable t) {
//                callback.onFailure(new Exception(t));
//            }
//        });
    }

    private interface QuizApi {
        @GET("api.php")
        Call<QuizQuestionsResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") String category,
                @Query("difficulty") String difficulty);
    }

    private interface QuizApiTriviaCategories {
        @GET("api_category.php")
        Call<TriviaCategoriesCallback> getTriviaCategories(
                @Query("category") String category);
    }
}