package com.ivione.myworkout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.textfield.TextInputLayout;

public class NewCompetition extends AppCompatActivity {

    TextView textHead;
    TextInputLayout placeText, competitionNameText, trackText, resultText;
    EditText dateText;
    Button btnSave, btnCancel;
    String license;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_competition);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        license = getIntent().getStringExtra("license");
        textHead = findViewById(R.id.textHead);
        textHead.setText(textHead.getText().toString() + " (" + license + ")");
        placeText = findViewById(R.id.placeText);
        competitionNameText = findViewById(R.id.competitionNameText);
        trackText = findViewById(R.id.trackText);
        resultText = findViewById(R.id.resultText);
        placeText = findViewById(R.id.placeText);
        dateText = findViewById(R.id.dateText);
        btnSave = findViewById(R.id.saveButton);
        btnCancel = findViewById(R.id.cancelButton);

        btnSave.setOnClickListener(view -> {
            String place = placeText.getEditText().getText().toString();
            String competitionName = competitionNameText.getEditText().getText().toString();
            String track = trackText.getEditText().getText().toString();
            String result = resultText.getEditText().getText().toString();
            String date = dateText.getText().toString();

            if (validateNewCompetition(place, competitionName, track, result, date)) {
                Competition competition = new Competition(license,
                        placeText.getEditText().getText().toString(),
                        competitionNameText.getEditText().getText().toString(),
                        trackText.getEditText().getText().toString(),
                        resultText.getEditText().getText().toString(),
                        dateText.getText().toString());
                db.competitionDao().insert(competition);

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Faltan campos por completar", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        btnCancel.setOnClickListener(v -> {
            finish();
        });

    }

    private boolean validateNewCompetition(String place, String competitionName, String track, String result, String date) {
        boolean isValid = true;
        if(place.isEmpty() || place == null) {
            isValid = false;
        }
        if(competitionName.isEmpty() || competitionName == null) {
            isValid = false;
        }
        if(track.isEmpty() || track == null) {
            isValid = false;
        }
        if(result.isEmpty() || result == null) {
            isValid = false;
        }
        if(date.isEmpty() || date == null) {
            isValid = false;
        }
        return isValid;
    }

}
