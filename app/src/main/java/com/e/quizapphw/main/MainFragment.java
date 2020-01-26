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
import android.widget.Button;
import android.widget.ImageView;

import com.e.quizapphw.R;
import com.e.quizapphw.core.CoreFragment;
import com.e.quizapphw.result.ResultActivity;

import java.util.Objects;

public class MainFragment extends CoreFragment {

    private MainViewModel mViewModel;
    private ImageView skip;

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
