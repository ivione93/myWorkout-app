package com.ivione.myworkout;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CompetitionDao {

    @Query("SELECT * FROM competition")
    List<Competition> getCompetitions();

    @Query("SELECT * FROM competition WHERE license = :license")
    List<Competition> getCompetitionsByLicense(String license);

    @Query("SELECT * FROM competition WHERE license = :license ORDER BY competition_date DESC")
    List<Competition> getLatestCompetitionByLicense(String license);

    @Insert
    void insert(Competition competition);

    @Query("DELETE FROM Competition WHERE license = :license")
    void deleteCompetitionByLicense(String license);
}
