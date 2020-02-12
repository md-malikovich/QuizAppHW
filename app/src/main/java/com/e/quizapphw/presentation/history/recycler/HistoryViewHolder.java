package com.e.quizapphw.presentation.history.recycler;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.e.quizapphw.R;
import com.e.quizapphw.model.History;

import java.util.ArrayList;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    TextView category, answers, difficulty, date;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        category = itemView.findViewById(R.id.tvHistory_category_name);
        answers = itemView.findViewById(R.id.tvHistory_answers_amount);
        difficulty = itemView.findViewById(R.id.tvHistory_difficulty_type);
        date = itemView.findViewById(R.id.tvHistory_date);
    }

    void onBind(ArrayList<History> historyArrayList, int position) {
        category.setText(historyArrayList.get(position).getCategory());
        answers.setText(historyArrayList.get(position).getCorrectAnswers());
        difficulty.setText(historyArrayList.get(position).getDifficulty());
        date.setText(String.valueOf(historyArrayList.get(position).getDate()));
    }
}
