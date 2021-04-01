package com.ivione.myworkout;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity
public class Training {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public Long id;

    @ColumnInfo(name = "license")
    public String license;

    @ColumnInfo(name = "training_date")
    public Date date;

    public Calentamiento calentamiento;

    public List<Series> series;

    public List<Cuestas> cuestas;

    public class Calentamiento {
        public String time;
        public String distance;
    }

    public class Series {
        public String distance;
        public int time;
        public boolean withHurdles;
    }

    public class Cuestas {
        public int times;
        public String type;
        public String time;
    }
}
