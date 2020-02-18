package com.e.quizapphw.presentation.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import com.e.quizapphw.App;
import com.e.quizapphw.BuildConfig;
import com.e.quizapphw.R;
import com.e.quizapphw.core.CoreFragment;
import com.e.quizapphw.presentation.main.MainViewModel;

import java.util.Objects;

public class SettingsFragment extends CoreFragment {

    private SettingsViewModel sViewModel;
    private TextView tvSettings_share, tvSettings_rate, tvSettings_feedback, tvSettings_clear;

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
        initView(view);
    }

    private void initView(View view) {
        tvSettings_share = view.findViewById(R.id.tvSettings_share);
        tvSettings_rate = view.findViewById(R.id.tvSettings_rate);
        tvSettings_feedback = view.findViewById(R.id.tvSettings_feedback);
        tvSettings_clear = view.findViewById(R.id.tvSettings_clear);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()))
                .get(SettingsViewModel.class);
        clickListener();
    }

    private void clickListener() {
        tvSettings_clear.setOnClickListener(v -> new AlertDialog.Builder(requireContext())
                .setTitle("Удаление истории")
                .setMessage("Вы хотите удалить историю?")

                .setPositiveButton("Да", (dialog, which) -> {
                    App.historyStorage.deleteAll();
                })

                .setNegativeButton("Нет", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show());
        tvSettings_share.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Сheck out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        });
    }
}
