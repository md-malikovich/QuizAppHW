package com.e.quizapphw.presentation.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.e.quizapphw.App;
import com.e.quizapphw.R;
import com.e.quizapphw.data.remote.IQuizApiClient;
import com.e.quizapphw.presentation.history.HistoryFragment;
import com.e.quizapphw.model.Question;
import com.e.quizapphw.presentation.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;
    private ViewPager mViewPager;
    private MainPagerAdapter mAdapter;
    private BottomNavigationView navView;

    @Override
    public void onBackPressed() {
        switch (mViewPager.getCurrentItem()) {
            case 0:
                super.onBackPressed();
                break;
            case 1:
                mViewPager.setCurrentItem(0);
                break;
            case 2:
                mViewPager.setCurrentItem(1);
                break;
        }
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.main_view_pager);
        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        navView = findViewById(R.id.nav_view);
        setBottomNavigationView();

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.getCategories();
        mViewModel.getGlobal();
        mViewModel.getQuestionsCount(9);
    }

    private void setBottomNavigationView() {

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



//        App.quizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
//            @Override
//            public void onSuccess(List<Question> questions) {
//                for (Question question : questions) {
//                    //Log.d("ololo", question.getQuestion() + " " + question.getDifficulty());
//                }
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                //Log.e("ololo", e.getMessage(), e);
//            }
//        });

//        App.quizApiClient.getTriviaCategories(new IQuizApiClient.TriviaCategoriesCallback() {
//            @Override
//            public void onSuccess(List<TriviaCategory> triviaCategories) {
//                //TODO:
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                //TODO:
//            }
//        });
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {

//        private List<Fragment> Fragment = new ArrayList<>(); //Fragment List
//        private List<String> NamePage = new ArrayList<>();

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

//        public void add(Fragment Frag, String Title) {
//            Fragment.add(Frag);
//            NamePage.add(Title);
//        }

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

//        @Override
//        public CharSequence getPageTitle(int position) {
//            return NamePage.get(position);
//        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
