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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.e.quizapphw.R;
import com.e.quizapphw.core.CoreFragment;
import com.e.quizapphw.model.EDifficulty;
import com.e.quizapphw.quiz.QuizActivity;
import com.e.quizapphw.result.ResultActivity;

import java.util.ArrayList;
import java.util.Objects;

public class MainFragment extends CoreFragment {

    private MainViewModel mViewModel;
    private ImageView skip;
    Spinner spinnerCategory, spinnerDifficulty;
    SeekBar seekBar;
    TextView tvAmount, tvMainQuAmount;
    Button btnStart;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private void initViews() {
        spinnerCategory = getActivity().findViewById(R.id.spinnerCategory);
        spinnerDifficulty = getActivity().findViewById(R.id.spinnerDifficulty);
        btnStart = getActivity().findViewById(R.id.btnStart);
        skip = getActivity().findViewById(R.id.imgQuiz);
        tvAmount = getActivity().findViewById(R.id.tvMainAmount);
        seekBar = getActivity().findViewById(R.id.seekBar);
        tvMainQuAmount = getActivity().findViewById(R.id.tvMainQuAmount);

    }

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
        initViews();
        mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()))
                .get(MainViewModel.class);

        mViewModel.message.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //Log.d("ololo", s);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                startActivity(intent);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                startActivity(intent);
            }
        });

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

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //tvMainQuAmount.setText(ratesValues.get(position).toString().trim());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });
    }


}
