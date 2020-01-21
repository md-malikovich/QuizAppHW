package com.e.quizapphw.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.e.quizapphw.onboard.OnBoardActivity;
import com.e.quizapphw.R;
import com.e.quizapphw.data.PreferenceHelper;
import com.e.quizapphw.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                selectActivity();
            }
        }, 1_000);
    }

    private void selectActivity() {
        if(PreferenceHelper.getIsFirstLaunch()) {
            MainActivity.start(this);
        } else {
            PreferenceHelper.setIsFirstLaunch();
            MainActivity.start(this);
            //OnBoardActivity.start(this);
        }
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        finish();
    }
}
