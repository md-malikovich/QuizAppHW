package com.e.quizapphw.data.remote;

import android.util.Log;
import com.e.quizapphw.core.CoreCallback;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiClient implements IQuizApiClient{
    private Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    private TriviaApi client = retrofit.create(TriviaApi.class);

    @Override
    public void getQuestions(int amount, Integer category, String difficulty, final QuestionsCallback callback) {
        Call<QuizQuestionsResponse> call = client.getQuestions(
                amount,
                category,
                difficulty
        );
        Log.e("ololo", "URL - 1: " + call.request().url());

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
    public void getTriviaCategories(TriviaCategoriesCallback callback) {
        final Call<QuizCategoriesResponse> call = client.getCategories();
        Log.e("ololo", "URL - 2: " + call.request().url());
        call.enqueue(new CoreCallback<QuizCategoriesResponse>() {
            @Override
            public void onSuccess(QuizCategoriesResponse result) {
                callback.onSuccess(result.getTriviaCategories());
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }

    @Override
    public void getCountGlobal(CountGlobalCallback callback) {
        final Call<QuizGlobalResponse> call = client.getCountGlobal();
        Log.e("ololo", "URL - 3: " + call.request().url());
        call.enqueue(new CoreCallback<QuizGlobalResponse>() {
            @Override
            public void onSuccess(QuizGlobalResponse result) {
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }

    @Override
    public void getQuestionCount(Integer category, QuestionCountCallback questionCount) {
        Call<QuizQuestionCount> call = client.getQuestionsCount(category);
        call.enqueue(new CoreCallback<QuizQuestionCount>() {
            @Override
            public void onSuccess(QuizQuestionCount result) {
                questionCount.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                questionCount.onFailure(e);
            }
        });
    }

    private interface TriviaApi {

        @GET("api.php")
        Call<QuizQuestionsResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") Integer category,
                @Query("difficulty") String difficulty);

        @GET("api_category.php")
        Call<QuizCategoriesResponse> getCategories();

        @GET("api_count_global.php")
        Call<QuizGlobalResponse> getCountGlobal();

        @GET("api_count.php")
        Call<QuizQuestionCount> getQuestionsCount(
                @Query("category") Integer category
        );
    }

//    @Override
//        public void getTriviaCategories(final TriviaCategoriesCallback triviaCategoriesCallback) {
//            final Call<TriviaCategoriesCallback> call2 = client2.getTriviaCategories("General Knowledge");
//        call2.enqueue(new Callback<QuizCategoriesResponse>() {
//            @Override
//            public void onResponse(Call<QuizCategoriesResponse> call, Response<QuizCategoriesResponse> response) {
//                if(response.isSuccessful() && response.body() != null) {
//                    triviaCategoriesCallback.onSuccess(response.body().getTriviaCategories());
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

        //            Log.d("ololo", "URL - " + call2.request().url());
//
//            call2.enqueue(new CoreCallback<TriviaCategoriesCallback>() {
//                @Override
//                public void onSuccess(TriviaCategoriesCallback result) {
//                    triviaCategoriesCallback.onSuccess(result.);
//                    Log.d("ololo", "onSuccess" + result.onSuccess(););
//                }
//
//                @Override
//                public void onFailure(Exception e) {
//                    Log.d("ololo", "onFailure");
//                }
//            });

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
//    }

//    private interface QuizApiTriviaCategories {
//        @GET("api_category.php")
//        Call<TriviaCategoriesCallback> getTriviaCategories(
//                @Query("category") String category);
//    }
}