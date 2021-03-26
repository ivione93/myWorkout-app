package com.ivione.myworkout;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Athlete.class, Competition.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AthleteDao athleteDao();
}
