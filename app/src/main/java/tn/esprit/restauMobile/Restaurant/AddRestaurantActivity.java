package tn.esprit.restauMobile.Restaurant;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tn.esprit.restauMobile.R;
import tn.esprit.restauMobile.database.AppDataBase;
import tn.esprit.restauMobile.entities.Restaurant;
import tn.esprit.restauMobile.services.MailService;

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
        String userEmail = getIntent().getStringExtra("userEmail");
        if (userEmail != null) {
            Toast.makeText(this, "User email: " + userEmail, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "User email not found", Toast.LENGTH_SHORT).show();
        }
        buttonSaveRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRestaurant();
            }
        });
    }


    // Dans la méthode saveRestaurant()
    private void saveRestaurant() {
        String name = editTextName.getText().toString().trim();
        String location = editTextLocation.getText().toString().trim();
        int userId = sharedPreferences.getInt("id", -1);
        String userEmail = sharedPreferences.getString("email", null);

        if (name.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setLocation(location);
        restaurant.setUserId(userId);

        db.restaurantDAO().insertRestaurant(restaurant);

        // Afficher un toast pour confirmer l'ajout du restaurant
        Toast.makeText(this, "Restaurant added successfully", Toast.LENGTH_SHORT).show();

        // Exécuter l'envoi d'e-mail dans un thread en arrière-plan
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (userEmail != null) {
                        MailService mailService = new MailService();
                        mailService.sendEmail(userEmail, "New Restaurant Added",
                                "A new restaurant has been added by user ID: " + userId);
                    } else {
                        System.out.println("User email not found.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Fermer l'activité après l'enregistrement
        finish();

}}

