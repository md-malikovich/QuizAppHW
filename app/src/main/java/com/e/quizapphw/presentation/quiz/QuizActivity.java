package com.e.quizapphw.presentation.quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.e.quizapphw.R;
import com.e.quizapphw.model.Question;
import com.e.quizapphw.presentation.main.MainActivity;
import com.e.quizapphw.presentation.quiz.recycler.QuizAdapter;
import com.e.quizapphw.presentation.quiz.recycler.QuizViewHolder;
import com.e.quizapphw.presentation.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuizViewHolder.Listener {

    //region Static
    private static String EXTRA_AMOUNT = "amount";
    private static String EXTRA_CATEGORY = "category";
    private static String EXTRA_DIFFICULTY = "difficulty";

    public static void start(Context context, Integer amount, Integer category, String difficulty) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_AMOUNT, amount);
        intent.putExtra(EXTRA_CATEGORY, category);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        context.startActivity(intent);
    }
    //endregion

    private RecyclerView recyclerView;
    private ProgressBar progressBarQuiz;
    private QuizViewModel viewModel;
    private QuizAdapter adapter;
    private Button btnSkip;
    private ImageView imgBack;

    private List<Question> questionsList = new ArrayList<>();

    private TextView tvQuizCategory, tvQuizProgress;
    private Integer category;
    private String difficulty;
    private int amount;
    private int amountQuantity;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        viewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        initView();
        getQuestions();
        recyclerBuilder();
//        viewModel.questions.observe(this, questions -> {
//            adapter.setQuestions(questions);
//        });
//        viewModel.currentQuestionPosition.observe(this, currentQuestion -> {
//            String hint = currentQuestion + 1 + "/" + adapter.getItemCount();
//            recyclerView.smoothScrollToPosition(currentQuestion);
//        });
        viewModel.openResultEvent.observe(this, resultId -> {//
        });
        viewModel.init(amount, category, difficulty);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void recyclerBuilder() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        adapter = new QuizAdapter(this); //adapter = new QuizAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener((v, event) -> true);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        tvQuizCategory = findViewById(R.id.tvQuizCategory);
        tvQuizProgress = findViewById(R.id.tvQuizProgress);
        recyclerView = findViewById(R.id.quiz_recycler);
        progressBarQuiz = findViewById(R.id.progressBarQuiz);
    }

    private void getQuestions() {
        amount = getIntent().getIntExtra(EXTRA_AMOUNT, 7);
        category = getIntent().getIntExtra(EXTRA_CATEGORY, 7);
        difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);
        Log.e("ololo", "getQuestions(): " + amount + " " + category + " " + difficulty);
        if (category == 8) {
            category = null;
            questionObserver();
        } else {
            questionObserver();
        }
    }

    private void questionObserver() {
        viewModel.init(amount, category, difficulty);
        viewModel.questions.observe(this, questions -> {
            questionsList = questions;
            adapter.updateQuestion(questions);
            getPosition();
        });
    }

    private void getPosition() {
        viewModel.currentQuestionPosition.observe(this, integer -> {
            recyclerView.scrollToPosition(integer - 1);
            tvQuizProgress.setText(integer + "/" + amount);
            progressBarQuiz.setProgress(integer);
            progressBarQuiz.setMax(amount);
            if (questionsList.size() > 0)
                tvQuizCategory.setText(questionsList.get(integer - 1).getCategory());
        });
    }

    public void imgBack_click(View view) {
        if (progressBarQuiz.getProgress() != 1) {
            viewModel.onBackPressed();
        } else {
            MainActivity.start(this);
            finish();
        }
    }

    public void btnBack_click(View view) {
        if (progressBarQuiz.getProgress() < amount) {
            viewModel.onSkipClick();
        } else {
            ResultActivity.start(this);
        }
    }

    @Override
    public void onAnswerClick(int position, int selectedAnswerPosition) {
        viewModel.onAnswerClick(position, selectedAnswerPosition);
    }
}