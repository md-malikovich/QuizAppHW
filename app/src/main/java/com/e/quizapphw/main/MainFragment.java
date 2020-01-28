package com.e.quizapphw.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.e.quizapphw.R;
import com.e.quizapphw.core.CoreFragment;
import com.e.quizapphw.quiz.QuizActivity;
import com.e.quizapphw.result.ResultActivity;

import java.util.ArrayList;
import java.util.Objects;

public class MainFragment extends CoreFragment {

    private MainViewModel mViewModel;
    private ImageView skip;
    Spinner spinnerCategory, spinnerDifficulty;
    SeekBar seekBar;
    TextView tvAmount;
    Button btnStart;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

//    private void initViews() {
//        spinnerCategory = (Spinner) getActivity().findViewById(R.id.spinnerCategory);
//        spinnerDifficulty = (Spinner) getActivity().findViewById(R.id.spinnerDifficulty);
//        ArrayList<String> category = new ArrayList<>();
//        category.add("1");
//        ArrayList<String> difficulty = new ArrayList<>();
//        ArrayAdapter adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, category);
//        ArrayAdapter adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, difficulty);
//        spinnerCategory.setAdapter(adapter1);
//        spinnerDifficulty.setAdapter(adapter2);
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()))
                .get(MainViewModel.class);

        mViewModel.message.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("ololo", s);
            }
        });

        btnStart = (Button) getActivity().findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                startActivity(intent);
            }
        });


        skip = (ImageView) getActivity().findViewById(R.id.imgQuiz);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                startActivity(intent);
            }
        });

        tvAmount = (TextView) getActivity().findViewById(R.id.tvMainAmount);
        seekBar =  (SeekBar) getActivity().findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAmount.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
            }
        });

    }
}
