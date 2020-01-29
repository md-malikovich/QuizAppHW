package com.e.quizapphw.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import com.e.quizapphw.App;
import com.e.quizapphw.R;
import com.e.quizapphw.data.remote.IQuizApiClient;
import com.e.quizapphw.history.HistoryFragment;
import com.e.quizapphw.model.Question;
import com.e.quizapphw.model.TriviaCategory;
import com.e.quizapphw.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;
    private ViewPager mViewPager;
    private MainPagerAdapter mAdapter;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mViewPager = findViewById(R.id.main_view_pager);
        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        navView = findViewById(R.id.nav_view);
        setBottomNavigationView();


    }

    private  void setBottomNavigationView() {

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_main:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_history:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_settings:
                        mViewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                navView.getMenu().getItem(position).setChecked(false);
            }
        });



        App.quizApiClient.getQuestions(new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> questions) {
                for (Question question : questions) {
                    //Log.d("ololo", question.getQuestion() + " " + question.getDifficulty());
                }
            }

            @Override
            public void onFailure(Exception e) {
                //Log.e("ololo", e.getMessage(), e);
            }
        });

//        App.quizApiClient.getTriviaCategories(new IQuizApiClient.TriviaCategoriesCallback() {
//            @Override
//            public void onSuccess(List<TriviaCategory> triviaCategories) {
//                //TODO:!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                //TODO:!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//            }
//        });
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;

            switch (position) {
                case 0:
                    fragment = MainFragment.newInstance();
                    break;
                case 1:
                    fragment = HistoryFragment.newInstance();
                    break;
                default:
                    fragment = SettingsFragment.newInstance();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
