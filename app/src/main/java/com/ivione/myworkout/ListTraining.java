package com.ivione.myworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ListTraining extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_training);

        getSupportActionBar().setTitle("Entrenamientos");
    }
}