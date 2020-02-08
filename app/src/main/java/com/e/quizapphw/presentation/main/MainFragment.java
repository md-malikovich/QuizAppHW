package com.e.quizapphw.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.e.quizapphw.R;
import com.e.quizapphw.core.CoreFragment;
import com.e.quizapphw.presentation.quiz.QuizActivity;
import com.e.quizapphw.presentation.result.ResultActivity;

import org.angmarch.views.NiceSpinner;

public class MainFragment extends CoreFragment {

    SeekBar seekBar;
    private NiceSpinner spinnerCategory;
    private NiceSpinner spinnerDifficulty;
    private MainViewModel mViewModel;
    private ImageView skip;
    TextView tvAmount, tvMainQuAmount;
    Button btnStart;
    //private View mStart;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }

    private void initViews() {
        seekBar = getActivity().findViewById(R.id.seekBar);
        spinnerCategory = getActivity().findViewById(R.id.spinnerCategory);
        spinnerDifficulty = getActivity().findViewById(R.id.spinnerDifficulty);
        btnStart = getActivity().findViewById(R.id.btnStart);
        skip = getActivity().findViewById(R.id.imgQuiz);
        tvAmount = getActivity().findViewById(R.id.tvMainAmount);
        tvMainQuAmount = getActivity().findViewById(R.id.tvMainQuAmount);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
//        mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()))
//                .get(MainViewModel.class);
//
//        mViewModel.message.observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                //Log.d("ololo", s);
//            }
//        });

        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), QuizActivity.class);
            startActivity(intent);
            QuizActivity.start(getContext(), 10, 6, "easy");

            Log.d("ololo", "Start properties - amount:" + seekBar.getProgress()
                    + " category: " + spinnerCategory.getSelectedIndex()
                    + " difficulty: " + spinnerDifficulty.getSelectedIndex()
            );
        });

        skip.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ResultActivity.class);
            startActivity(intent);
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
