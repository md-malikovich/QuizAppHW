package com.e.quizapphw.data.db.converters;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;
import java.util.Date;

public class TimestampConverter {

    @TypeConverter
    public static Date fromRaw(@Nullable Long timestamp) {
        if (timestamp == null) return null;

        return new Date(timestamp); //TODO:
    }

    @TypeConverter
    public static Long toRaw(@Nullable Date date) {
        if (date == null) return null;

        return date.getTime();
    }
}
