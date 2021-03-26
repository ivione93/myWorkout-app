package com.ivione.myworkout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
            String license = licenseText.getEditText().getText().toString();
            String name = nameText.getEditText().getText().toString();
            String surname = surnameText.getEditText().getText().toString();
            String birthdate = birthdateText.getText().toString();

            if (validateNewAthlete(license, name, surname, birthdate)) {
                if (db.athleteDao().getAthleteByLicense(licenseText.getEditText().getText().toString()) != null) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Ya existe un atleta con esa licencia", Toast.LENGTH_LONG);
                    toast.show();
                } else {

                    Athlete athlete = new Athlete(licenseText.getEditText().getText().toString(),
                            nameText.getEditText().getText().toString(),
                            surnameText.getEditText().getText().toString(),
                            birthdateText.getText().toString());
                    db.athleteDao().insert(athlete);

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Faltan campos por completar", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    private boolean validateNewAthlete(String license, String name, String surname, String birthdate) {
        boolean isValid = true;
        if(license.isEmpty() || license == null) {
            isValid = false;
        }
        if(name.isEmpty() || name == null) {
            isValid = false;
        }
        if(surname.isEmpty() || surname == null) {
            isValid = false;
        }
        if(birthdate.isEmpty() || birthdate == null) {
            isValid = false;
        }
        return isValid;
    }
}
