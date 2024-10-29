package tn.esprit.restauMobile.Client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tn.esprit.restauMobile.MainActivity;
import tn.esprit.restauMobile.R;

public class ClientActivity extends Activity {
    Button  navProfile, navSettings, navSignOut;
    SharedPreferences sharedPreferences;
    TextView clientNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);


        sharedPreferences = getSharedPreferences("LoginPreferences", MODE_PRIVATE);

        String clientName = sharedPreferences.getString("username", "Client");  // Default name is "Client"


        clientNameTextView = findViewById(R.id.clientName);
        clientNameTextView.setText("Welcome, "+ clientName +" !");

        // Initialize navbar buttons

        navProfile = findViewById(R.id.nav_profile);
        navSettings = findViewById(R.id.nav_settings);
        navSignOut = findViewById(R.id.nav_signout);

        navProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        navSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Settings navigation
            }
        });


        navSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    // Sign-out method to clear SharedPreferences and redirect to login screen
    private void signOut() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Redirect to MainActivity (login screen)
        Intent intent = new Intent(ClientActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
