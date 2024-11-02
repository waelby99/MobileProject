package tn.esprit.restauMobile.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "stock", foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")
})
public class Stock {

    @PrimaryKey(autoGenerate = true)
    private int stockId;

    @ColumnInfo(name = "productName")
    private String productName;

    @ColumnInfo(name = "quantity")
    private int quantity;

    // Relation avec User (un-à-plusieurs)
    private int userId;


}

