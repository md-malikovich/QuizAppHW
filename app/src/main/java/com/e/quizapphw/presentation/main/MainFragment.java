package com.e.quizapphw.presentation.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

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
import com.e.quizapphw.utils.SimpleSeekBarChangeListener;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainFragment extends CoreFragment {

    SeekBar seekBar;
    private NiceSpinner spinnerCategory;
    private NiceSpinner spinnerDifficulty;
    private MainViewModel viewModel;
    TextView tvAmount, tvMainQuAmount;
    Button btnStart;
    private String difficult;
    private Integer category;
    //private View mStart;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        seekBar = getActivity().findViewById(R.id.seekBar);
        spinnerCategory = getActivity().findViewById(R.id.spinnerCategory);
        spinnerDifficulty = getActivity().findViewById(R.id.spinnerDifficulty);
        btnStart = getActivity().findViewById(R.id.btnStart);
        tvAmount = getActivity().findViewById(R.id.tvMainAmount);
        tvMainQuAmount = getActivity().findViewById(R.id.tvMainQuAmount);

        setSpinners(getResources().getStringArray(R.array.categories), spinnerCategory);
        setSpinners(getResources().getStringArray(R.array.difficulties), spinnerDifficulty);

        seekBar.setOnSeekBarChangeListener(new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAmount.setText(String.valueOf(progress));
            }
        });

        btnStart.setOnClickListener(v -> {
            category = spinnerCategory.getSelectedIndex() + 8;
            spinnerDifficulty.getSelectedIndex();
            QuizActivity.start(getContext(), seekBar.getProgress(), category, getDifficultyId());

            Log.d("ololo", "Start properties - amount:" + seekBar.getProgress()
                    + " category: " + spinnerCategory.getSelectedIndex()
                    + " difficulty: " + spinnerDifficulty.getSelectedIndex()
            );
        });
    }

    private String getDifficultyId() {
        switch (spinnerDifficulty.getSelectedIndex()) {
            case 0:
                difficult = null;
                break;
            case 1:
                difficult = "easy";
                break;
            case 2:
                difficult = "medium";
                break;
            case 3:
                difficult = "hard";
                break;
        }
        return difficult;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);

        viewModel.finishEvent.observe(this, aVoid -> {
            Log.d("ololo", "Finish");
        });

        viewModel.messageEvent.observe(this, message -> {
            Log.d("ololo", "Message " + message);
        });

        viewModel.callFinish();
        viewModel.onShowMessageClick();

//        mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()))
//                .get(MainViewModel.class);

//        mViewModel.message.observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                //Log.d("ololo", s);
//            }
//        });
    }

    private void setSpinners(String[] array, NiceSpinner spinner) {
        List<String> list = new LinkedList<>(Arrays.asList(array));
        spinner.attachDataSource(list);
    }
}
