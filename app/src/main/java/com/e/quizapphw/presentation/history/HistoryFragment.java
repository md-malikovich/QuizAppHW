package com.e.quizapphw.presentation.history;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.quizapphw.App;
import com.e.quizapphw.R;
import com.e.quizapphw.core.CoreFragment;
import com.e.quizapphw.model.History;
import com.e.quizapphw.presentation.history.recycler.HistoryAdapter;
import com.e.quizapphw.presentation.history.recycler.HistoryViewHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryFragment extends CoreFragment implements HistoryViewHolder.Listener {

    private HistoryViewModel mViewModel;
    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private List<History> histories;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.history_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.history_recycler);
        recyclerViewBuilder();
        adapter.updateHistory(new ArrayList<>());
    }

    private void recyclerViewBuilder() {
//        adapter = new HistoryAdapter();
//
//        for (int i = 1; i < 30; i++) {
//            History history = new History("Mixed", "Hard", 8, new Date(), 0, i);
//            adapter.updateHistory(history);
//        }
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        adapter = new HistoryAdapter(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(HistoryViewModel.class);

//        mViewModel.history.observe(getActivity(), histories -> {
//            this.histories = histories;
//            if (histories != null) {
//                adapter.updateHistory(histories);
//            }
//        });

    }

    private void showPopupMenu(View view, int position) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.inflate(R.menu.menu_popup);

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.popup_delete:
                    App.historyStorage.deleteById(histories.get(position).getId());
                    return true;
                case R.id.popup_share:
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            "Game: QuizApp" +
                                    "\nCategory name: " + histories.get(position).getCorrectAnswers() +
                                    "\nCorrect answers: " + histories.get(position).getCorrectAnswers() + "/" +
                                    histories.get(position).getAmount() +
                                    "\nDifficulty: " + histories.get(position).getDifficulty() +
                                    "\nDate: " + histories.get(position).getDate());
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                    return true;
            }
            return false;
        });
        popupMenu.setOnDismissListener(menu -> Toast.makeText(getContext(), "OnDismissListener",
                Toast.LENGTH_SHORT).show());
        popupMenu.show();
    }

    @Override
    public void onClick(View view, int position) {
        showPopupMenu(view, position);
    }
}
