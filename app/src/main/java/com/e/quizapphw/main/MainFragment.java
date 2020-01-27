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
import android.widget.Spinner;

import com.e.quizapphw.R;
import com.e.quizapphw.core.CoreFragment;
import com.e.quizapphw.result.ResultActivity;

import java.util.ArrayList;
import java.util.Objects;

public class MainFragment extends CoreFragment {

    private MainViewModel mViewModel;
    private ImageView skip;
    Spinner spinnerCategory, spinnerDifficulty;

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

        skip = (ImageView) getActivity().findViewById(R.id.imgQuiz);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                startActivity(intent);
            }
        });
    }



}
