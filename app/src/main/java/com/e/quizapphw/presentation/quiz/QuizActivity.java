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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.e.quizapphw.R;
import com.e.quizapphw.model.Question;
import com.e.quizapphw.presentation.quiz.recycler.QuizAdapter;
import com.e.quizapphw.presentation.quiz.recycler.QuizViewHolder;

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

    private List<Question> questionsList = new ArrayList<>();

    private TextView tvQuizCategory, tvQuizProgress;
    private Integer category;
    private String difficulty;
    private int amount;
    private int amountQuantity;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        viewModel = ViewModelProviders.of(this).get(QuizViewModel.class);

        initView();

        viewModel.questions.observe(this, questions -> {
            adapter.setQuestions(questions);
        });

        viewModel.currentQuestionPosition.observe(this, currentQuestion -> {
            String hint = currentQuestion + 1 + "/" + adapter.getItemCount();
            recyclerView.smoothScrollToPosition(currentQuestion);
        });

        viewModel.openResultEvent.observe(this, resultId -> {
            //
        });

        viewModel.init(10, 0, "");
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        recyclerView = findViewById(R.id.quiz_recycler);

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
        adapter = new QuizAdapter(this::onAnswerClick); //adapter = new QuizAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener((v, event) -> true);

        findViewById(R.id.btnSkip).setOnClickListener(v -> {
            viewModel.onSkipClick();
            Log.d("ololo", "skip");
        });

        getIntent().getIntExtra(EXTRA_AMOUNT, 10);
        progressBarQuiz = findViewById(R.id.progressBarQuiz);
    }

    @Override
    public void onAnswerClick(int position, int selectedAnswerPosition) {
        viewModel.onAnswerClick(position, selectedAnswerPosition);
    }
}