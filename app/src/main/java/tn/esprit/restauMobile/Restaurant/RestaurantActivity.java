package tn.esprit.restauMobile.Restaurant;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tn.esprit.restauMobile.MainActivity;
import tn.esprit.restauMobile.R;

public class RestaurantActivity extends Activity {
    Button navHome, navProfile, navSettings, navSignOut;
    SharedPreferences sharedPreferences;
    Button buttonAddRestaurant, buttonShowRestaurants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("LoginPreferences", MODE_PRIVATE);

        // Example content for restaurant-specific activity
        TextView textView = findViewById(R.id.textView);
        textView.setText("Welcome, Restaurant Owner!");
        buttonAddRestaurant = findViewById(R.id.add_restaurant_button);
        buttonShowRestaurants = findViewById(R.id.nav_profile);

        // Initialize navbar buttons
        navHome = findViewById(R.id.nav_home);
        navSettings = findViewById(R.id.nav_settings);
        navSignOut = findViewById(R.id.nav_signout);
        buttonAddRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantActivity.this, AddRestaurantActivity.class);
                startActivity(intent);
            }
        });
        buttonShowRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Démarrer l'activité RestaurantsListActivity
                Intent intent = new Intent(RestaurantActivity.this, RestaurantsListActivity.class);
                startActivity(intent);
            }
        });


        // Set up navbar button listeners
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Home navigation (can start a new activity or stay in current one)
            }
        });



        navSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Settings navigation
            }
        });

        // Handle Sign Out
        navSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();  // Call the signOut method
            }
        });

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
