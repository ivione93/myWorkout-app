package com.ivione.myworkout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Athlete> listAthletes = new ArrayList<>();
    RecyclerView rvAthletes;
    ImageButton btnBack;

    AdapterAthlete adapter = new AdapterAthlete(listAthletes);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Atletas");

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        rvAthletes = findViewById(R.id.recycler_view);
        rvAthletes.setLayoutManager(new LinearLayoutManager(this));

        listAthletes.clear();
        listAthletes.addAll(db.athleteDao().getAthletes());

        rvAthletes.setAdapter(adapter);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    public void addAthlete(View view) {
        Intent intent = new Intent(this, NewAthlete.class);
        startActivity(intent);
    }

}