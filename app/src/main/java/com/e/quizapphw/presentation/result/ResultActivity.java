package com.e.quizapphw.presentation.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.quizapphw.R;
import com.e.quizapphw.presentation.main.MainActivity;

public class ResultActivity extends AppCompatActivity {

    ResultViewModel resultViewModel;
    //private Button finish;
    private static String EXTRA_QUIZ_ID = "result_id";
    private Integer id;
    private TextView difficultValue;
    private TextView correctAnswerAmount;
    private TextView resultPercent;
    private TextView category;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        id = getIntent().getIntExtra(EXTRA_QUIZ_ID, 0);
        initView();
        resultViewModel.getResult(id);
        setViewContent();
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

//        finish = findViewById(R.id.btnFinish);
//        finish.setOnClickListener(v -> {
//            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
//            startActivity(intent);
//        });
    }

    private void setViewContent() {
        resultViewModel.quizResult.observe(this, quizResult -> {
            category.setText(quizResult.getCategory());
            difficultValue.setText(quizResult.getDifficulty());
            correctAnswerAmount.setText(quizResult.getCorrectAnswersAmount() + "/" + quizResult.getQuestions().size());
            int correctAnswersPercent = (int) ((double) quizResult.getCorrectAnswersAmount() / quizResult.getQuestions().size() * 100);
            resultPercent.setText(correctAnswersPercent + " %");
        });
    }

    private void initView() {
        difficultValue = findViewById(R.id.tvResult_difficulty2);
        correctAnswerAmount = findViewById(R.id.tvAnswerResult2);
        resultPercent = findViewById(R.id.tv_result2);
        category = findViewById(R.id.tvResult_category2);
        imageView = findViewById(R.id.imgResult);
    }

    public static void start(Context context, Integer id) {
        context.startActivity(new Intent(context, ResultActivity.class).putExtra(EXTRA_QUIZ_ID, id));
    }

    public void btnFinish(View view) {
        finish();
    }
}
