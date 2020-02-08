package com.e.quizapphw.presentation.history;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.e.quizapphw.R;
import com.e.quizapphw.core.CoreFragment;

public class HistoryFragment extends CoreFragment {

    private HistoryViewModel mViewModel;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.history_fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);

        mViewModel.history.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //Log.d("ololo", s);
            }
        });
    }
}
