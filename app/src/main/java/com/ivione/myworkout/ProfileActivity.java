package com.ivione.myworkout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.ivione.myworkout.ui.login.LoginActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    CircleImageView photoProfile;
    TextView nameProfile, emailProfile, birthProfile, licenseProfile;
    ImageButton btnSignOut;
    Button btnGoToCompetitions, getBtnGoToTrainings;

    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;

    AppDatabase db;
    String license;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        license = getIntent().getStringExtra("license");

        photoProfile = findViewById(R.id.photoProfile);
        nameProfile = findViewById(R.id.nameProfile);
        emailProfile = findViewById(R.id.emailProfile);
        birthProfile = findViewById(R.id.birthProfile);
        licenseProfile = findViewById(R.id.licenseProfile);
        btnSignOut = findViewById(R.id.btnSignOut);
        btnGoToCompetitions = findViewById(R.id.btnGoToCompetitions);
        getBtnGoToTrainings = findViewById(R.id.btnGoToTrainings);

        btnSignOut.setOnClickListener(v -> signOut());

        btnGoToCompetitions.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListCompetitions.class);
            intent.putExtra("license", license);
            startActivity(intent);
        });

        getBtnGoToTrainings.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Athlete athlete = db.athleteDao().getAthleteByLicense(license);

        if(account != null) {
            if (account.getPhotoUrl() != null) {
                Glide.with(this).load(account.getPhotoUrl()).into(photoProfile);
            } else {

            }
            nameProfile.setText(athlete.name + " " + athlete.surname);
            emailProfile.setText(account.getEmail());
            licenseProfile.setText(athlete.license);
            birthProfile.setText(athlete.birthdate);
        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, task -> {
                    Intent mainIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(mainIntent);
                });
    }

}