package com.e.quizapphw.presentation.history.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.e.quizapphw.R;
import com.e.quizapphw.model.History;
import com.e.quizapphw.presentation.quiz.recycler.QuizViewHolder;

import java.util.ArrayList;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    TextView category, answers, difficulty, date;
    private ImageView dots;
    private HistoryViewHolder.Listener listener;

    public HistoryViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        this.listener = listener;
        category = itemView.findViewById(R.id.tvHistory_category_name);
        answers = itemView.findViewById(R.id.tvHistory_answers_amount);
        difficulty = itemView.findViewById(R.id.tvHistory_difficulty_type);
        date = itemView.findViewById(R.id.tvHistory_date);
        dots = itemView.findViewById(R.id.imgHistory_dots);
        dots.setOnClickListener(v -> listener.onClick(v, getAdapterPosition()));
    }

    void onBind(History history) {
        category.setText(history.getCategory());
        answers.setText(history.getCorrectAnswers() + "/" + history.getAmount());
        difficulty.setText(history.getDifficulty());
        date.setText(history.getDate().toString());
        //date.setText(String.valueOf(history.getDate()));

    }

    public interface Listener {
        void onClick(View view, int id);
    }
}
