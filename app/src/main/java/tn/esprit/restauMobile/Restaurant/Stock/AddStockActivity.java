package tn.esprit.restauMobile.Restaurant.Stock;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tn.esprit.restauMobile.Client.ProfileActivity;
import tn.esprit.restauMobile.MainActivity;
import tn.esprit.restauMobile.R;
import tn.esprit.restauMobile.daos.StockDAO;
import tn.esprit.restauMobile.database.AppDataBase;
import tn.esprit.restauMobile.entities.Stock;

public class AddStockActivity extends Activity {

    private EditText productNameEditText, quantityEditText;
    private Button saveButton, navHome, navProfile, navMesStocks, navSignOut;
    private StockDAO stockDAO;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);

        productNameEditText = findViewById(R.id.productNameEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        saveButton = findViewById(R.id.saveButton);

        // Initialize database and SharedPreferences
        stockDAO = AppDataBase.getAppDataBase(this).stockDAO();
        sharedPreferences = getSharedPreferences("LoginPreferences", MODE_PRIVATE);

        // Navbar buttons
        navHome = findViewById(R.id.nav_home);
        navProfile = findViewById(R.id.nav_profile);
        navMesStocks = findViewById(R.id.nav_mes_stocks);
        navSignOut = findViewById(R.id.nav_signout);

        navHome.setOnClickListener(v -> {
            // Handle Home navigation
        });

        navProfile.setOnClickListener(v -> {
            Intent intent = new Intent(AddStockActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        navMesStocks.setOnClickListener(v -> {
            Intent intent = new Intent(AddStockActivity.this, MesStocksActivity.class);
            startActivity(intent);
        });

        navSignOut.setOnClickListener(v -> signOut());

        // Add stock functionality
        saveButton.setOnClickListener(v -> {
            String productName = productNameEditText.getText().toString();
            int quantity = Integer.parseInt(quantityEditText.getText().toString());
            int userId = sharedPreferences.getInt("id", -1);

            if (userId != -1) {
                Stock newStock = new Stock();
                newStock.setProductName(productName);
                newStock.setQuantity(quantity);
                newStock.setUserId(userId);

                stockDAO.insertStock(newStock);

                Toast.makeText(AddStockActivity.this, "Stock added successfully", Toast.LENGTH_SHORT).show();
                // Don't redirect, just clear the fields
                productNameEditText.setText("");
                quantityEditText.setText("");
            } else {
                Toast.makeText(AddStockActivity.this, "User not found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Sign-out method to clear SharedPreferences and redirect to login screen
    private void signOut() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(AddStockActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
