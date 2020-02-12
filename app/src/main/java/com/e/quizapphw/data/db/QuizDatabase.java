package com.e.quizapphw.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.e.quizapphw.data.history.HistoryDao;
import com.e.quizapphw.model.QuizResult;

@Database(
        entities = {QuizResult.class},
        version = 1,
        exportSchema = false)

public abstract class QuizDatabase extends RoomDatabase {
    public abstract HistoryDao historyDao();
}



