package com.ivione.myworkout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
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
    ImageButton btnBack, btnNewCompetition;

    String license;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_competitions);

        getSupportActionBar().setTitle("Competiciones");

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        rvCompetitions = findViewById(R.id.rvCompetitions);
        rvCompetitions.setLayoutManager(new LinearLayoutManager(this));

        license = getIntent().getStringExtra("license");
        listCompetitions.clear();
        listCompetitions.addAll(db.competitionDao().getCompetitionsByLicense(license));

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnNewCompetition = findViewById(R.id.btnNewCompetition);
        btnNewCompetition.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewCompetition.class);
            intent.putExtra("license", license);
            startActivity(intent);
        });

        rvCompetitions.setAdapter(adapter);
    }
}