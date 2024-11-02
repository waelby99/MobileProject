package tn.esprit.restauMobile.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "menu",foreignKeys = { @ForeignKey(entity = Restaurant.class, parentColumns = "restaurantId", childColumns = "restaurantId")})
public class Menu {

    @PrimaryKey(autoGenerate = true)
    private int menuId;

    @ColumnInfo(name = "description")
    private String description;

    // Relation avec Restaurant (un-à-un)
    private int restaurantId;


}

