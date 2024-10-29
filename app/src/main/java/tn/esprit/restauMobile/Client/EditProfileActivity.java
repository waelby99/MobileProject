package tn.esprit.restauMobile.Client;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tn.esprit.restauMobile.R;

public class EditProfileActivity extends Activity {

    EditText editName, editEmail, editPhone;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button saveProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        sharedPreferences = getSharedPreferences("LoginPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);
        saveProfileButton = findViewById(R.id.saveProfileButton);

        loadCurrentProfile();

        saveProfileButton.setOnClickListener(v -> {
            saveProfileChanges();
        });
    }

    private void loadCurrentProfile() {
        String name = sharedPreferences.getString("username", "");
        String email = sharedPreferences.getString("email", "");
        String phone = sharedPreferences.getString("phone", "");

        editName.setText(name);
        editEmail.setText(email);
        editPhone.setText(phone);
    }

    private void saveProfileChanges() {
        String newName = editName.getText().toString();
        String newEmail = editEmail.getText().toString();
        String newPhone = editPhone.getText().toString();

        editor.putString("username", newName);
        editor.putString("email", newEmail);
        editor.putString("phone", newPhone);
        editor.apply();

        Toast.makeText(this, "Profile updated!", Toast.LENGTH_SHORT).show();
        finish();  // Close the activity after saving
    }
}
