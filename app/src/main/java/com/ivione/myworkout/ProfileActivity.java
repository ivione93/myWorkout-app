package com.ivione.myworkout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.ivione.myworkout.ui.login.LoginActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    CircleImageView photoProfile;
    TextView nameProfile, emailProfile, idProfile;
    ImageButton btnSignOut;
    Button btnGoToCompetitions, getBtnGoToTrainings;

    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        photoProfile = findViewById(R.id.photoProfile);
        nameProfile = findViewById(R.id.nameProfile);
        emailProfile = findViewById(R.id.emailProfile);
        idProfile = findViewById(R.id.idProfile);
        btnSignOut = findViewById(R.id.btnSignOut);
        btnGoToCompetitions = findViewById(R.id.btnGoToCompetitions);
        getBtnGoToTrainings = findViewById(R.id.btnGoToTrainings);

        btnSignOut.setOnClickListener(v -> signOut());

        btnGoToCompetitions.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListCompetitions.class);
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

        if(account != null) {
            if (account.getPhotoUrl() != null) {
                Glide.with(this).load(account.getPhotoUrl()).into(photoProfile);
            } else {

            }
            nameProfile.setText(account.getDisplayName());
            emailProfile.setText(account.getEmail());
            idProfile.setText(account.getId());
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