package tn.esprit.restauMobile.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.restauMobile.entities.Restaurant;

@Dao
public interface RestaurantDAO {

    @Insert
    void insertRestaurant(Restaurant restaurant);

    @Delete
    void deleteRestaurant(Restaurant restaurant);

    @Update
    void updateRestaurant(Restaurant restaurant);

    @Query("SELECT * FROM restaurant")
    List<Restaurant> getAllRestaurants();

    @Query("SELECT * FROM restaurant WHERE restaurantId = :id")
    Restaurant getRestaurantById(int id);

    @Query("SELECT * FROM restaurant WHERE userId = :userId")
    Restaurant getRestaurantByUserId(int userId);
    @Query("SELECT * FROM restaurant WHERE userId = :userId")
    List<Restaurant> getRestaurantsByUserId(int userId);
}
