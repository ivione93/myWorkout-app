package com.ivione.myworkout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ivione.myworkout.ui.login.LoginActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    CircleImageView photoProfile;
    TextView nameProfile, emailProfile, birthProfile, licenseProfile;
    TextView last_competition_name, last_competition_place, last_competition_date, last_competition_track, last_competition_result;
    ImageButton btnSignOut;
    BottomNavigationView navigation_menu;

    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;

    AppDatabase db;
    String license;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("Perfil");

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        license = getIntent().getStringExtra("license");

        initReferences();

        getLastCompetition(db, license);

        btnSignOut.setOnClickListener(v -> signOut());

        navigation_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_athletes) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.action_training) {

                } else if (item.getItemId() == R.id.action_competitions) {
                    Intent intent = new Intent(getApplicationContext(), ListCompetitions.class);
                    intent.putExtra("license", license);
                    startActivity(intent);
                }
                return true;
            }
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

    private void initReferences() {
        photoProfile = findViewById(R.id.photoProfile);
        nameProfile = findViewById(R.id.nameProfile);
        emailProfile = findViewById(R.id.emailProfile);
        birthProfile = findViewById(R.id.birthProfile);
        licenseProfile = findViewById(R.id.licenseProfile);
        btnSignOut = findViewById(R.id.btnSignOut);
        last_competition_name = findViewById(R.id.last_competition_name);
        last_competition_place = findViewById(R.id.last_competition_place);
        last_competition_date = findViewById(R.id.last_competition_date);
        last_competition_track = findViewById(R.id.last_competition_track);
        last_competition_result = findViewById(R.id.last_competition_result);
        navigation_menu = findViewById(R.id.navigation_menu);
    }

    private void getLastCompetition(AppDatabase db, String license) {
        List<Competition> last_competition = db.competitionDao().getLatestCompetitionByLicense(license);

        if (last_competition.isEmpty()) {
            last_competition_name.setText("No se han encontrado competiciones");
        } else {
            last_competition_name.setText(last_competition.get(0).name);
            last_competition_place.setText(last_competition.get(0).place);
            last_competition_date.setText(Utils.toString(last_competition.get(0).date));
            last_competition_track.setText(last_competition.get(0).track);
            last_competition_result.setText(last_competition.get(0).result);
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