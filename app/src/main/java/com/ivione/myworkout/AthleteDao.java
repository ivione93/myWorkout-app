package com.ivione.myworkout;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AthleteDao {

    @Query("SELECT * FROM athlete")
    List<Athlete> getAthletes();

    @Insert
    void insert(Athlete athlete);

    @Delete
    void delete(Athlete athlete);
}
