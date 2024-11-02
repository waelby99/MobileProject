package tn.esprit.restauMobile.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.restauMobile.entities.Menu;

@Dao
public interface MenuDAO {

    @Insert
    void insertMenu(Menu menu);

    @Delete
    void deleteMenu(Menu menu);

    @Update
    void updateMenu(Menu menu);

    @Query("SELECT * FROM menu")
    List<Menu> getAllMenus();

    @Query("SELECT * FROM menu WHERE menuId = :id")
    Menu getMenuById(int id);

    @Query("SELECT * FROM menu WHERE restaurantId = :restaurantId")
    Menu getMenuByRestaurantId(int restaurantId);
}
