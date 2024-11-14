package tn.esprit.restauMobile.Restaurant;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tn.esprit.restauMobile.R;
import tn.esprit.restauMobile.database.AppDataBase;
import tn.esprit.restauMobile.entities.Restaurant;

public class AddRestaurantActivity extends Activity {

    private EditText editTextName, editTextLocation;
    private Button buttonSaveRestaurant;
    private AppDataBase db;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_restaurant_activity);

        editTextName = findViewById(R.id.edit_text_restaurant_name);
        editTextLocation = findViewById(R.id.edit_text_restaurant_location);
        buttonSaveRestaurant = findViewById(R.id.button_save_restaurant);

        db = AppDataBase.getAppDataBase(this);
        sharedPreferences = getSharedPreferences("LoginPreferences", MODE_PRIVATE);

        buttonSaveRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRestaurant();
            }
        });
    }

    private void saveRestaurant() {
        String name = editTextName.getText().toString().trim();
        String location = editTextLocation.getText().toString().trim();
        int userId = sharedPreferences.getInt("id", -1);

        if (name.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setLocation(location);
        restaurant.setUserId(userId);

        db.restaurantDAO().insertRestaurant(restaurant);

        Toast.makeText(this, "Restaurant added successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}

