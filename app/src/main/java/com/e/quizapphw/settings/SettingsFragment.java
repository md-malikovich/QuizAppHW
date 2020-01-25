package com.e.quizapphw.settings;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import com.e.quizapphw.R;
import com.e.quizapphw.core.CoreFragment;
import com.e.quizapphw.main.MainViewModel;

public class SettingsFragment extends CoreFragment {

    private SettingsViewModel sViewModel;
    private MainViewModel mViewModel;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.settings_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sViewModel=ViewModelProviders.of(getActivity())
                .get(SettingsViewModel.class);

        mViewModel = ViewModelProviders.of(getActivity())
                .get(MainViewModel.class);


    }
}
