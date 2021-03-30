package com.ivione.myworkout;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Athlete.class, Competition.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AthleteDao athleteDao();
    public abstract CompetitionDao competitionDao();
}
