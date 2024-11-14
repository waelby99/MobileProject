package tn.esprit.restauMobile.Restaurant;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import tn.esprit.restauMobile.Client.ProfileActivity;
import tn.esprit.restauMobile.MainActivity;
import tn.esprit.restauMobile.R;
import tn.esprit.restauMobile.Restaurant.Stock.MesStocksActivity;

public class RestaurantActivity extends Activity {
    Button navHome, navProfile, navMesStocks, navSignOut;  // Updated the variable name
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("LoginPreferences", MODE_PRIVATE);

        // Example content for restaurant-specific activity
        TextView textView = findViewById(R.id.textView);
        textView.setText("Welcome, Restaurant Owner!");

        // Initialize navbar buttons
        navHome = findViewById(R.id.nav_home);
        navProfile = findViewById(R.id.nav_profile);
        navMesStocks = findViewById(R.id.nav_mes_stocks);  // Updated ID to "nav_mes_stocks"
        navSignOut = findViewById(R.id.nav_signout);

        // Set up navbar button listeners
        navHome.setOnClickListener(v -> {
            // Handle Home navigation (can start a new activity or stay in current one)
        });

        navProfile.setOnClickListener(v -> {
            Intent intent = new Intent(RestaurantActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        navMesStocks.setOnClickListener(v -> {
            Intent intent = new Intent(RestaurantActivity.this, MesStocksActivity.class);
            startActivity(intent);
        });

        // Handle Sign Out
        navSignOut.setOnClickListener(v -> signOut());
    }

    // Sign-out method to clear SharedPreferences and redirect to login screen
    private void signOut() {
        // Clear the login state
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Redirect to MainActivity (login screen)
        Intent intent = new Intent(RestaurantActivity.this, MainActivity.class);
        startActivity(intent);
        finish();  // Finish current activity so user can't return to it
    }
}
