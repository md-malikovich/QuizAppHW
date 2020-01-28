package com.e.quizapphw.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.e.quizapphw.R;

public class QuizActivity extends AppCompatActivity {

    ProgressBar progressBarQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        progressBarQuiz = findViewById(R.id.progressBarQuiz);

    }
}
