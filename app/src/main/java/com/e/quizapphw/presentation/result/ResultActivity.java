package com.e.quizapphw.presentation.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.e.quizapphw.R;
import com.e.quizapphw.presentation.main.MainActivity;

public class ResultActivity extends AppCompatActivity {

    ResultViewModel resultViewModel;
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

        finish = findViewById(R.id.btnFinish);
        finish.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, ResultActivity.class));
    }
}
