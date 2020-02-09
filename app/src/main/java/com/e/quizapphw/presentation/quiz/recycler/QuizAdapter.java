package com.e.quizapphw.presentation.quiz.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.quizapphw.R;
import com.e.quizapphw.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private QuizViewHolder.Listener listener;
    private List<Question> questionsList = new ArrayList<>();

    public QuizAdapter(QuizViewHolder.Listener listener) {
        this.listener = listener;
    }

    public void updateQuestion(List<Question> list) {
        this.questionsList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz, parent, false);
        return new QuizViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof QuizViewHolder) {
            ((QuizViewHolder) holder).onBind(questionsList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    public void setQuestions(List<Question> questions) {
        this.questionsList.clear();
        this.questionsList.addAll(questions);
        notifyDataSetChanged();
    }
}
