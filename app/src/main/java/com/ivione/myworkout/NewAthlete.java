package com.ivione.myworkout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class NewAthlete extends AppCompatActivity {

    TextView textView2;
    EditText licenseText, nameText, surnameText, birthDateInput;
    Button saveButton;

    RequestQueue requestQueue;
    private static final String URL = "http://127.0.0.1:8080/workout/athlete/M1599/competition/all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_athlete);
        getSupportActionBar().hide();

        requestQueue = Volley.newRequestQueue(this);
        textView2 = findViewById(R.id.textView2);
        jsonArrayRequest();

        licenseText = findViewById(R.id.licenseText);
        nameText = findViewById(R.id.nameText);
        surnameText = findViewById(R.id.surnameText);
        birthDateInput = findViewById(R.id.birthDateInput);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(view -> {
            String enviarCorreo = "ivgarciag@yahoo.es";
            String enviarAsunto = "[MyWorkout] Prueba de envío";
            String enviarMensaje = "Licencia: " + licenseText.getText().toString() +
                    " Nombre: " + nameText.getText().toString() + " " + surnameText.getText().toString();

            Intent intent = new Intent(Intent.ACTION_SEND);

            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{enviarCorreo});
            intent.putExtra(Intent.EXTRA_SUBJECT, enviarAsunto);
            intent.putExtra(Intent.EXTRA_TEXT, enviarMensaje);

            intent.setType("message/rfc822");

            startActivity(Intent.createChooser(intent, "Elije un cliente de correo"));
        });
    }

    private void jsonArrayRequest() {
        JsonArrayRequest json = new JsonArrayRequest(Request.Method.GET, URL, null, response -> {
            int size = response.length();
            for (int i=0; i<size; i++) {
                try {
                    JSONObject jsonObject = new JSONObject(response.get(i).toString());
                    String licencia = jsonObject.getString("license");
                    textView2.append("License: " + licencia + "/n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> {

        });
        requestQueue.add(json);
    }
}
