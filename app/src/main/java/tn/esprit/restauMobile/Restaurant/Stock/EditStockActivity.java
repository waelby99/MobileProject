package tn.esprit.restauMobile.Restaurant.Stock;

import android.app.Activity;
import android.content.Intent;
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

public class EditStockActivity extends Activity {

    private EditText editProductName, editQuantity;
    private Button updateStockButton, navHome, navProfile, navMesStocks, navSignOut;
    private StockDAO stockDAO;
    private int stockId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stock);

        editProductName = findViewById(R.id.editProductName);
        editQuantity = findViewById(R.id.editQuantity);
        updateStockButton = findViewById(R.id.updateStockButton);

        // Initialize database DAO
        stockDAO = AppDataBase.getAppDataBase(this).stockDAO();

        // Retrieve stock ID passed from StockAdapter
        stockId = getIntent().getIntExtra("stockId", -1);
        Stock stock = stockDAO.getStockById(stockId);

        if (stock != null) {
            editProductName.setText(stock.getProductName());
            editQuantity.setText(String.valueOf(stock.getQuantity()));
        }

        // Update stock details
        updateStockButton.setOnClickListener(v -> {
            stock.setProductName(editProductName.getText().toString());
            stock.setQuantity(Integer.parseInt(editQuantity.getText().toString()));
            stockDAO.updateStock(stock);
            Toast.makeText(EditStockActivity.this, "Stock updated successfully", Toast.LENGTH_SHORT).show();

        });

        // Initialize bottom navigation bar buttons
        navHome = findViewById(R.id.nav_home);
        navProfile = findViewById(R.id.nav_profile);
        navMesStocks = findViewById(R.id.nav_mes_stocks);
        navSignOut = findViewById(R.id.nav_signout);

        // Set onClickListeners for navigation
        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(EditStockActivity.this, MainActivity.class);
            startActivity(intent);
        });

        navProfile.setOnClickListener(v -> {
            Intent intent = new Intent(EditStockActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        navMesStocks.setOnClickListener(v -> {
            Intent intent = new Intent(EditStockActivity.this, MesStocksActivity.class);
            startActivity(intent);
        });

        navSignOut.setOnClickListener(v -> signOut());
    }

    // Sign out method
    private void signOut() {
        // Implement sign out functionality here
        Toast.makeText(this, "Signed out successfully", Toast.LENGTH_SHORT).show();
        // Redirect to login or main screen after sign out
        Intent intent = new Intent(EditStockActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
