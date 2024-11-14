package tn.esprit.restauMobile.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "menu",foreignKeys = {
        @ForeignKey(entity = Restaurant.class, parentColumns = "restaurantId", childColumns = "restaurantId")})
public class Menu {

    @PrimaryKey(autoGenerate = true)
    private int menuId;

    @ColumnInfo(name = "description")
    private String description;

    // Relation avec Restaurant (un-à-un)
    private int restaurantId;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Menu(int menuId, int restaurantId, String description) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.description = description;
    }

    public Menu() {
    }

}

