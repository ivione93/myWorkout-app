package com.ivione.myworkout;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Competition {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public Long id;

    @ColumnInfo(name = "license")
    public String license;

    @ColumnInfo(name = "place")
    public String place;

    @ColumnInfo(name = "competition_name")
    public String name;

    @ColumnInfo(name = "competition_date")
    public String date;

    @ColumnInfo(name = "track")
    public String track;

    @ColumnInfo(name = "result")
    public String result;

    public Competition(@NonNull Long id, String license, String place, String name, String date, String track, String result) {
        this.id = id;
        this.license = license;
        this.place = place;
        this.name = name;
        this.date = date;
        this.track = track;
        this.result = result;
    }
}
