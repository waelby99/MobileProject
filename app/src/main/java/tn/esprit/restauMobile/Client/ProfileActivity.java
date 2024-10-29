package tn.esprit.restauMobile.Client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import tn.esprit.restauMobile.R;

public class ProfileActivity extends Activity {

    TextView displayName, displayEmail, displayPhone;
    SharedPreferences sharedPreferences;
    ImageButton editProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferences = getSharedPreferences("LoginPreferences", MODE_PRIVATE);

        displayName = findViewById(R.id.displayName);
        displayEmail = findViewById(R.id.displayEmail);
        displayPhone = findViewById(R.id.displayPhone);
        //editProfileButton = findViewById(R.id.editProfileButton);


        loadProfileDetails();
        /*editProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            startActivity(intent);
        });*/
    }

    private void loadProfileDetails() {
        String name = sharedPreferences.getString("username", "Unknown");
        String email = sharedPreferences.getString("email", "Not Provided");
        String phone = sharedPreferences.getString("phone", "Not Provided");

        displayName.setText(name);
        displayEmail.setText(email);
        displayPhone.setText(phone);
    }

}