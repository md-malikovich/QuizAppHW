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
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.e.quizapphw.R;
import com.e.quizapphw.presentation.quiz.recycler.QuizAdapter;
import com.e.quizapphw.presentation.quiz.recycler.QuizViewHolder;

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

    ProgressBar progressBarQuiz;
    private QuizViewModel viewModel;
    private RecyclerView recyclerView;
    private QuizAdapter adapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initView();

        viewModel = ViewModelProviders.of(this)
                .get(QuizViewModel.class);

        viewModel.questions.observe(this, questions -> {
            adapter.setQuestions(questions);
        });

        viewModel.currentQuestionPosition.observe(this, currentQuestion -> {
            String hint = currentQuestion + 1 + "/" + adapter.getItemCount();

            recyclerView.smoothScrollToPosition(currentQuestion);
        });

        viewModel.openResultEvent.observe(this, resultId -> {

        });

        viewModel.init(10, 0, "");
    }

    private void initView() {
        getIntent().getIntExtra(EXTRA_AMOUNT, 10);
        progressBarQuiz = findViewById(R.id.progressBarQuiz);

        adapter = new QuizAdapter(this::onAnswerClick);
        recyclerView = findViewById(R.id.quiz_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this,
                RecyclerView.HORIZONTAL,
                false));
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener((v, event) -> true);

        findViewById(R.id.btnSkip).setOnClickListener(v -> {
            viewModel.onSkipClick();
            Log.d("ololo", "skip");
        });
    }

    @Override
    public void onAnswerClick(int position, int selectedAnswerPosition) {
        viewModel.onAnswerClick(position, selectedAnswerPosition);
    }
}
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
//        return super.onCreateView(name, context, attrs);
//    }