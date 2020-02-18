package com.e.quizapphw.presentation.quiz.recycler;

import android.graphics.Color;
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
    private Button btn1,btn2,btn3,btn4, booleanBtnTrue, booleanBtnFalse;

    QuizViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        initViews(itemView);
        clickListener();
        this.listener = listener;
    }

    private void initViews(View itemView) {
        btn1=itemView.findViewById(R.id.btnAnswer1);
        btn2=itemView.findViewById(R.id.btnAnswer2);
        btn3=itemView.findViewById(R.id.btnAnswer3);
        btn4=itemView.findViewById(R.id.btnAnswer4);
        booleanBtnTrue=itemView.findViewById(R.id.booleanBtnTrue);
        booleanBtnFalse=itemView.findViewById(R.id.booleanBtnFalse);
        multiple=itemView.findViewById(R.id.quiz_linear_btnMultiple);
        booleans=itemView.findViewById(R.id.quiz_linear_btnSimple);
        tvQuizQuestion=itemView.findViewById(R.id.tvQuizQuestion);
    }

    private void clickListener() {
        btn1.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 0));
        btn2.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 1));
        btn3.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 2));
        btn4.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 3));
        booleanBtnTrue.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 0));
        booleanBtnFalse.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 1));
    }

    private void setButton(Boolean enabled) {
        btn1.setEnabled(enabled);
        btn2.setEnabled(enabled);
        btn3.setEnabled(enabled);
        btn4.setEnabled(enabled);
        booleanBtnTrue.setEnabled(enabled);
        booleanBtnFalse.setEnabled(enabled);
    }

    private void resetButtons() {
        resetAnswerButtons(btn1, btn2, btn3, btn4, booleanBtnTrue, booleanBtnFalse);
    }

    private void resetAnswerButtons(Button... buttons) {
        for (Button button : buttons) {
            button.setBackgroundResource(R.drawable.btn_style);
            button.setTextColor(itemView.getResources().getColor(R.color.colorBlue));
        }
    }

    private void setTextButtons(Question question, Button... buttons) {
        int i = 0;
        for (Button button : buttons) {
            button.setText(Html.fromHtml(question.getAnswers().get(i)));
            i++;
        }
    }

    void onBind(Question question) {
//        tvQuizQuestion.setText(Html.fromHtml(question.getQuestion()));
//        if (question.getType() == EType.MULTIPLE) {
//            btn1.setText(Html.fromHtml(
//                    question.getAnswers().get(0)));
//            btn2.setText(Html.fromHtml(
//                    question.getAnswers().get(1)));
//            btn3.setText(Html.fromHtml(
//                    question.getAnswers().get(2)));
//            btn4.setText(Html.fromHtml(
//                    question.getAnswers().get(3)));
//            multiple.setVisibility(View.VISIBLE);
//            booleans.setVisibility(View.INVISIBLE);
//        } else {
//            booleans.setVisibility(View.VISIBLE);
//            multiple.setVisibility(View.INVISIBLE);
//        }

        resetButtons();
        if (question.getSelectedAnswerPosition() == null) {
            setButton(true);
        } else {
            setButton(false);
        }
        tvQuizQuestion.setText(Html.fromHtml(
                question.getQuestion()));
        if (question.getType() == EType.MULTIPLE) {
            multiple.setVisibility(View.VISIBLE);
            booleans.setVisibility(View.INVISIBLE);
            setTextButtons(question, btn1, btn2, btn3, btn4);
        } else {
            booleans.setVisibility(View.VISIBLE);
            multiple.setVisibility(View.INVISIBLE);
        }
        if (question.getSelectedAnswerPosition() != null) {
            setSelected(question);
        }
    }

    private void setSelected(Question question) {
        if (question.getSelectedAnswerPosition() != null) {
            switch (question.getSelectedAnswerPosition()) {
                case 0:
                    if (question.getCorrectAnswer().equals(question.getAnswers().get(0))) {
                        btn1.setBackgroundResource(R.drawable.btn_correct_answer);
                        booleanBtnTrue.setBackgroundResource(R.drawable.btn_correct_answer);
                        btn1.setTextColor(Color.WHITE);
                        booleanBtnTrue.setTextColor(Color.WHITE);
                    } else {
                        btn1.setBackgroundResource(R.drawable.btn_incorrect_answer);
                        booleanBtnTrue.setBackgroundResource(R.drawable.btn_incorrect_answer);
                        btn1.setTextColor(Color.WHITE);
                        booleanBtnTrue.setTextColor(Color.WHITE);
                    }
                    break;
                case 1:
                    if (question.getCorrectAnswer().equals(question.getAnswers().get(1))) {
                        btn2.setBackgroundResource(R.drawable.btn_correct_answer);
                        booleanBtnFalse.setBackgroundResource(R.drawable.btn_correct_answer);
                        btn2.setTextColor(Color.WHITE);
                        booleanBtnFalse.setTextColor(Color.WHITE);
                    } else {
                        btn2.setBackgroundResource(R.drawable.btn_incorrect_answer);
                        booleanBtnFalse.setBackgroundResource(R.drawable.btn_incorrect_answer);
                        btn2.setTextColor(Color.WHITE);
                        booleanBtnFalse.setTextColor(Color.WHITE);
                    }
                    break;
                case 2:
                    if (question.getAnswers().get(2).equals(question.getCorrectAnswer())) {
                        btn3.setBackgroundResource(R.drawable.btn_correct_answer);
                        btn3.setTextColor(Color.WHITE);
                    } else {
                        btn3.setBackgroundResource(R.drawable.btn_incorrect_answer);
                        btn3.setTextColor(Color.WHITE);
                    }
                    break;
                case 3:
                    if (question.getAnswers().get(3).equals(question.getCorrectAnswer())) {
                        btn4.setBackgroundResource(R.drawable.btn_correct_answer);
                        btn4.setTextColor(Color.WHITE);
                    } else {
                        btn4.setBackgroundResource(R.drawable.btn_incorrect_answer);
                        btn4.setTextColor(Color.WHITE);
                    }
                    break;
            }
        }
    }

    public interface Listener {
        void onAnswerClick(int position, int selectedAnswerPosition);
    }
}
