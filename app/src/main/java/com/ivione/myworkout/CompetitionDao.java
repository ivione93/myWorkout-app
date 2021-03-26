package com.ivione.myworkout;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CompetitionDao {

    @Query("SELECT * FROM competition WHERE license = :license")
    List<Competition> getCompetitionsByLicense(String license);

    @Insert
    void insert(Competition competition);

    @Query("DELETE FROM Competition")
    void delete();
}
