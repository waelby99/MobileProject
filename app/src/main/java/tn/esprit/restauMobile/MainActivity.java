package tn.esprit.restauMobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tn.esprit.restauMobile.Client.ClientActivity;
import tn.esprit.restauMobile.Restaurant.RestaurantActivity;
import tn.esprit.restauMobile.database.AppDataBase;
import tn.esprit.restauMobile.entities.User;
import tn.esprit.restauMobile.util.HashingUtil;

public class MainActivity extends Activity {
    EditText username, password;
    Button eregister, elogin;
    AppDataBase db;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eregister = findViewById(R.id.register1);
        elogin = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        db = AppDataBase.getAppDataBase(this);

        sharedPreferences = getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE);

        checkLoginStatus();

        eregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(in);
            }
        });

        elogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputUsername = username.getText().toString().trim();
                String inputPassword = password.getText().toString().trim();

                if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = db.userDAO().getUserByUsername(inputUsername);

                if (user != null) {
                    String hashedInputPassword = HashingUtil.hashPassword(inputPassword);
                    if (user.getPassword().equals(hashedInputPassword)) {
                        saveUserToLocalStorage(user);
                        String role = user.getRole();
                        if (role.equals("ROLE_CLIENT")) {
                            Intent intent = new Intent(MainActivity.this, ClientActivity.class);
                            startActivity(intent);
                        } else if (role.equals("ROLE_RESTAURANT")) {
                            Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid role", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void saveUserToLocalStorage(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", user.getLogin());
        editor.putString("role", user.getRole());
        editor.putString("email", user.getEmail());
        editor.putString("phone", user.getNumTel());
        editor.putInt("id", user.getId());
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
        Toast.makeText(MainActivity.this, "User logged in", Toast.LENGTH_SHORT).show();
    }

    private void checkLoginStatus() {
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            String role = sharedPreferences.getString("role", "");
            if (role.equals("ROLE_CLIENT")) {
                Intent intent = new Intent(MainActivity.this, ClientActivity.class);
                startActivity(intent);
                finish();
            } else if (role.equals("ROLE_RESTAURANT")) {
                Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
