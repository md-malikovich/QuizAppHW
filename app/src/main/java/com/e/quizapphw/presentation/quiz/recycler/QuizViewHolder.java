package com.e.quizapphw.presentation.quiz.recycler;

import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.quizapphw.R;
import com.e.quizapphw.model.EType;
import com.e.quizapphw.model.Question;

public class QuizViewHolder extends RecyclerView.ViewHolder {

    private Listener listener;
    private TextView tvQuizQuestion;
    private LinearLayout multiple,booleans;
    private Button btn1,btn2,btn3,btn4;

    QuizViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        btn1=itemView.findViewById(R.id.btnAnswer1);
        btn2=itemView.findViewById(R.id.btnAnswer2);
        btn3=itemView.findViewById(R.id.btnAnswer3);
        multiple=itemView.findViewById(R.id.quiz_linear_btnMultiple);
        booleans=itemView.findViewById(R.id.quiz_linear_btnSimple);
        btn4=itemView.findViewById(R.id.btnAnswer4);
        tvQuizQuestion=itemView.findViewById(R.id.tvQuizQuestion);
        this.listener = listener;

        //listener.onAnswerClick(getAdapterPosition(), 0);
    }

    void onBind(Question question) {
        tvQuizQuestion.setText(Html.fromHtml(question.getQuestion()));
        if (question.getType() == EType.MULTIPLE) {
            btn1.setText(Html.fromHtml(
                    question.getAnswers().get(0)));
            btn2.setText(Html.fromHtml(
                    question.getAnswers().get(1)));
            btn3.setText(Html.fromHtml(
                    question.getAnswers().get(2)));
            btn4.setText(Html.fromHtml(
                    question.getAnswers().get(3)));
            multiple.setVisibility(View.VISIBLE);
            booleans.setVisibility(View.INVISIBLE);
        } else {
            booleans.setVisibility(View.VISIBLE);
            multiple.setVisibility(View.INVISIBLE);
        }
    }

    public interface Listener {
        void onAnswerClick(int position, int selectedAnswerPosition);
    }
}
