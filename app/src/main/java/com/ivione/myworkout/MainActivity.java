package com.ivione.myworkout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Athlete> listAthletes = new ArrayList<>();
    RecyclerView rvAthletes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        rvAthletes = findViewById(R.id.recycler_view);
        rvAthletes.setLayoutManager(new LinearLayoutManager(this));

        //addListAthletes();

        if(getIntent().getSerializableExtra("license") != null) {
            String license = (String) getIntent().getSerializableExtra("license");
            String name = (String) getIntent().getSerializableExtra("name");
            String surname = (String) getIntent().getSerializableExtra("surname");
            String birthdate = (String) getIntent().getSerializableExtra("birthdate");
            Athlete athlete = new Athlete(license.toString(), name.toString(), surname.toString(), birthdate.toString());
            addListAthletes(athlete);
        }

        AdapterAthlete adapter = new AdapterAthlete(listAthletes);
        rvAthletes.setAdapter(adapter);
    }

    public void addListAthletes(Athlete athlete) {
        listAthletes.add(athlete);
    }

    public void addAthlete(View view) {
        Intent intent = new Intent(this, NewAthlete.class);
        startActivity(intent);
    }

}