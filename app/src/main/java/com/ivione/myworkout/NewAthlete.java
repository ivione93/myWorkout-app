package com.ivione.myworkout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NewAthlete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_athlete);
        getSupportActionBar().hide();
    }
}
