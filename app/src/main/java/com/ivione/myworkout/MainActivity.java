package com.ivione.myworkout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Athlete> listAthletes;
    RecyclerView rvAthletes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        listAthletes = new ArrayList<>();
        rvAthletes = findViewById(R.id.recycler_view);
        rvAthletes.setLayoutManager(new LinearLayoutManager(this));

        addListAthletes();

        AdapterAthlete adapter = new AdapterAthlete(listAthletes);
        rvAthletes.setAdapter(adapter);
    }

    private void addListAthletes() {
        listAthletes.add(new Athlete("M1599","Iván", "García Gómez", "18/06/1993"));
    }

    public void addAthlete(View view) {
        Intent intent = new Intent(this, NewAthlete.class);
        startActivity(intent);
    }

}