package tn.esprit.restauMobile.Restaurant.Stock;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tn.esprit.restauMobile.Client.ProfileActivity;
import tn.esprit.restauMobile.MainActivity;
import tn.esprit.restauMobile.R;
import tn.esprit.restauMobile.daos.StockDAO;
import tn.esprit.restauMobile.database.AppDataBase;
import tn.esprit.restauMobile.entities.Stock;

public class MesStocksActivity extends Activity {

    private Button addStockButton, navHome, navProfile, navMesStocks, navSignOut;
    private RecyclerView recyclerView;
    private StockAdapter stockAdapter;
    private List<Stock> stockList;
    private StockDAO stockDAO;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_stocks);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("LoginPreferences", MODE_PRIVATE);

        // Initialize database DAO and recycler view
        stockDAO = AppDataBase.getAppDataBase(this).stockDAO();
        recyclerView = findViewById(R.id.recyclerViewStocks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up "Add Stock" button
        addStockButton = findViewById(R.id.addStockButton);
        addStockButton.setOnClickListener(v -> {
            Intent intent = new Intent(MesStocksActivity.this, AddStockActivity.class);
            startActivity(intent);
        });

        // Navbar buttons
        navHome = findViewById(R.id.nav_home);
        navProfile = findViewById(R.id.nav_profile);
        navMesStocks = findViewById(R.id.nav_mes_stocks);
        navSignOut = findViewById(R.id.nav_signout);

        navHome.setOnClickListener(v -> {
            // Handle Home navigation
        });

        navProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MesStocksActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        navMesStocks.setOnClickListener(v -> {
            Intent intent = new Intent(MesStocksActivity.this, MesStocksActivity.class);
            startActivity(intent);
        });

        navSignOut.setOnClickListener(v -> signOut());

        // Fetch stocks from the database and update UI
        fetchStocks();
    }

    private void fetchStocks() {
        // Get the user ID from SharedPreferences
        int userId = sharedPreferences.getInt("id", -1);

        // Check if user ID is valid
        if (userId == -1) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            return;
        }

        // Fetch stocks by user ID
        stockList = stockDAO.getStocksByUserId(userId);

        // Check if stocks are available for the user
        if (stockList.isEmpty()) {
            Toast.makeText(this, "No stocks available for this user", Toast.LENGTH_SHORT).show();
        } else {
            stockAdapter = new StockAdapter(stockList, stockDAO); // Pass StockDAO here
            recyclerView.setAdapter(stockAdapter);
        }
    }


    private void signOut() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(MesStocksActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
