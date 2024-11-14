package tn.esprit.restauMobile.Restaurant;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import tn.esprit.restauMobile.R;
import tn.esprit.restauMobile.entities.Restaurant;
import tn.esprit.restauMobile.database.AppDataBase;

public class RestaurantsListActivity extends Activity {

    private ListView listViewRestaurants;
    private AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_list);

        // Retrieve userId passed through Intent
        int userId = getIntent().getIntExtra("userId", -1);

        // Handle invalid userId
        if (userId == -1) {
            Toast.makeText(this, "Invalid user ID", Toast.LENGTH_SHORT).show();
            finish();  // Optionally finish the activity or handle it as appropriate
            return;
        }

        // Initialize the database
        db = AppDataBase.getAppDataBase(getApplicationContext());

        // Initialize the ListView
        listViewRestaurants = findViewById(R.id.listViewRestaurants);

        // Retrieve and display the list of restaurants based on userId
        new GetRestaurantsTask().execute(userId);
    }

    // AsyncTask to fetch restaurants based on userId
    private class GetRestaurantsTask extends AsyncTask<Integer, Void, List<Restaurant>> {

        @Override
        protected List<Restaurant> doInBackground(Integer... params) {
            int userId = params[0];  // Get userId passed from onCreate
            // Fetch the list of restaurants based on the userId
            return db.restaurantDAO().getRestaurantsByUserId(userId);  // Use DAO to get filtered list
        }

        @Override
        protected void onPostExecute(List<Restaurant> restaurantList) {
            super.onPostExecute(restaurantList);

            if (restaurantList != null && !restaurantList.isEmpty()) {
                // Create an array of restaurant names and locations
                String[] restaurantNames = new String[restaurantList.size()];
                for (int i = 0; i < restaurantList.size(); i++) {
                    restaurantNames[i] = restaurantList.get(i).getName() + " - " + restaurantList.get(i).getLocation();
                }

                // Create an ArrayAdapter to display the list of restaurants
                ArrayAdapter<String> adapter = new ArrayAdapter<>(RestaurantsListActivity.this,
                        android.R.layout.simple_list_item_1, restaurantNames);
                listViewRestaurants.setAdapter(adapter);
            } else {
                // If no restaurants are found, show a default message
                ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(RestaurantsListActivity.this,
                        android.R.layout.simple_list_item_1, new String[]{"Aucun restaurant trouvé"});
                listViewRestaurants.setAdapter(emptyAdapter);
            }
        }
    }
}
