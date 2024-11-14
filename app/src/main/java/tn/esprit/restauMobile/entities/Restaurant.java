package tn.esprit.restauMobile.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurant", foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")
})
public class Restaurant {

    @PrimaryKey(autoGenerate = true)
    private int restaurantId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "location")
    private String location;

    // Relation avec User (un-à-un)
    private int userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Restaurant() {
    }

    public Restaurant(int restaurantId, String name, String location, int userId) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.userId = userId;
    }
}

