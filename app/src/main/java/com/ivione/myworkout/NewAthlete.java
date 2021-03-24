package com.ivione.myworkout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.textfield.TextInputLayout;

public class NewAthlete extends AppCompatActivity {

    TextInputLayout licenseText, nameText, surnameText;
    EditText birthdateText;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_athlete);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        //getSupportActionBar().hide();

        licenseText = findViewById(R.id.licenseText);
        nameText = findViewById(R.id.nameText);
        surnameText = findViewById(R.id.surnameText);
        birthdateText = findViewById(R.id.birthdateText);
        btnSave = findViewById(R.id.saveButton);
        btnSave.setOnClickListener(view -> {
            Athlete athlete = new Athlete(licenseText.getEditText().getText().toString(),
                    nameText.getEditText().getText().toString(),
                    surnameText.getEditText().getText().toString(),
                    birthdateText.getText().toString());
            db.athleteDao().insert(athlete);
            
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}
