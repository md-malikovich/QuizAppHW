package com.e.quizapphw.presentation.history.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.quizapphw.R;
import com.e.quizapphw.model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {

    private List<History> historyList = new ArrayList<>();
    private HistoryViewHolder.Listener listener;

    public HistoryAdapter(HistoryViewHolder.Listener listener) {
        this.listener = listener;
    }

    public void add(History story) {
        historyList.add(story);
        notifyDataSetChanged();
    }

    public void updateHistory(List<History> list) {
        this.historyList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_view_holder, parent, false);
        return new HistoryViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.onBind(historyList.get(position));
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }
}