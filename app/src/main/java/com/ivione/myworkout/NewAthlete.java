package com.ivione.myworkout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;

public class NewAthlete extends AppCompatActivity {

    TextInputLayout licenseText, nameText, surnameText;
    EditText birthdateText;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_athlete);
        //getSupportActionBar().hide();

        licenseText = findViewById(R.id.licenseText);
        nameText = findViewById(R.id.nameText);
        surnameText = findViewById(R.id.surnameText);
        birthdateText = findViewById(R.id.birthdateText);
        btnSave = findViewById(R.id.saveButton);
        btnSave.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("license", licenseText.getEditText().getText().toString());
            intent.putExtra("name", nameText.getEditText().getText().toString());
            intent.putExtra("surname", surnameText.getEditText().getText().toString());
            intent.putExtra("birthdate", birthdateText.getText().toString());
            startActivity(intent);
        });
    }
}
