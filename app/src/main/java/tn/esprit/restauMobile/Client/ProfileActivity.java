package tn.esprit.restauMobile.Client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tn.esprit.restauMobile.R;
import tn.esprit.restauMobile.database.AppDataBase;
import tn.esprit.restauMobile.entities.User;

public class ProfileActivity extends Activity {

    EditText editName, editEmail, editPhone;
    SharedPreferences sharedPreferences;
    Button saveButton,changePasswordButton;;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferences = getSharedPreferences("LoginPreferences", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("id", -1);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);
        saveButton = findViewById(R.id.saveButton);
        changePasswordButton = findViewById(R.id.changePasswordButton);

        if (userId != -1) {
            user = AppDataBase.getAppDataBase(this).userDAO().getUserById(userId);
            if (user != null) {
                loadProfileDetails();
            } else {
                Toast.makeText(this, "User not found in database", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "User ID not found in SharedPreferences", Toast.LENGTH_SHORT).show();
        }

        saveButton.setOnClickListener(v -> saveProfileDetails());
        changePasswordButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        });
    }

    private void loadProfileDetails() {
        if (user != null) {
            editName.setText(user.getLogin());
            editEmail.setText(user.getEmail());
            editPhone.setText(user.getNumTel());
        }
    }

    private void saveProfileDetails() {
        if (user != null) {
            user.setLogin(editName.getText().toString());
            user.setEmail(editEmail.getText().toString());
            user.setNumTel(editPhone.getText().toString());

            // Update user in the database
            AppDataBase.getAppDataBase(this).userDAO().updateUser(user);

            // Update SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", user.getLogin());
            editor.putString("email", user.getEmail());
            editor.putString("phone", user.getNumTel());
            editor.apply();

            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
