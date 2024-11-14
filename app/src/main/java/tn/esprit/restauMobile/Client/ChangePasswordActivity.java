package tn.esprit.restauMobile.Client;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tn.esprit.restauMobile.R;
import tn.esprit.restauMobile.database.AppDataBase;
import tn.esprit.restauMobile.entities.User;
import tn.esprit.restauMobile.util.HashingUtil;

public class ChangePasswordActivity extends Activity {

    EditText currentPassword, newPassword, confirmPassword;
    Button submitButton;
    SharedPreferences sharedPreferences;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        currentPassword = findViewById(R.id.currentPassword);
        newPassword = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        submitButton = findViewById(R.id.submitButton);

        sharedPreferences = getSharedPreferences("LoginPreferences", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("id", -1);

        if (userId != -1) {
            user = AppDataBase.getAppDataBase(this).userDAO().getUserById(userId);
        } else {
            Toast.makeText(this, "User ID not found in SharedPreferences", Toast.LENGTH_SHORT).show();
            finish(); // Exit if no user ID
        }

        submitButton.setOnClickListener(v -> {
            if (validatePasswords()) {
                String hashedCurrentPassword = HashingUtil.hashPassword(currentPassword.getText().toString());
                String hashedNewPassword = HashingUtil.hashPassword(newPassword.getText().toString());
                if (hashedCurrentPassword != null && user != null && user.getPassword().equals(hashedCurrentPassword)) {
                    user.setPassword(hashedNewPassword);
                    AppDataBase.getAppDataBase(this).userDAO().updateUser(user);
                    Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Current password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validatePasswords() {
        String oldPass = currentPassword.getText().toString();
        String newPass = newPassword.getText().toString();
        String confirmPass = confirmPassword.getText().toString();

        if (oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!newPass.equals(confirmPass)) {
            Toast.makeText(this, "New password and confirmation do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
