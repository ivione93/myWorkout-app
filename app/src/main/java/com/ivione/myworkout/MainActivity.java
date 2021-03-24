package com.ivione.myworkout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Athlete> listAthletes = new ArrayList<>();
    public static List<Athlete> listDB = new ArrayList<>();
    RecyclerView rvAthletes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        //getSupportActionBar().hide();

        rvAthletes = findViewById(R.id.recycler_view);
        rvAthletes.setLayoutManager(new LinearLayoutManager(this));

        listAthletes.clear();
        listDB = db.athleteDao().getAthletes();
        listAthletes.addAll(listDB);

        AdapterAthlete adapter = new AdapterAthlete(listAthletes);
        rvAthletes.setAdapter(adapter);
    }

    public void addAthlete(View view) {
        Intent intent = new Intent(this, NewAthlete.class);
        startActivity(intent);
    }

    public void deleteAthlete(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Se eliminará el atleta", Toast.LENGTH_SHORT);
        toast.show();
    }

}