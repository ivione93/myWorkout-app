package com.ivione.myworkout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class ListCompetitions extends AppCompatActivity {

    public static List<Competition> listCompetitions = new ArrayList<>();

    RecyclerView rvCompetitions;
    AdapterCompetition adapter = new AdapterCompetition(listCompetitions);

    String license;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_competitions);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        rvCompetitions = findViewById(R.id.rvCompetitions);
        rvCompetitions.setLayoutManager(new LinearLayoutManager(this));

        license = getIntent().getStringExtra("license");
        listCompetitions.clear();
        listCompetitions.addAll(db.competitionDao().getCompetitionsByLicense(license));

        rvCompetitions.setAdapter(adapter);
    }

}