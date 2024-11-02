package tn.esprit.restauMobile.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "reservation",foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")
})
public class Reservation {

    @PrimaryKey(autoGenerate = true)
    private int reservationId;

    @ColumnInfo(name = "restaurantId")
    private int restaurantId;

    // Relation avec User (un-à-plusieurs)

    private int userId;

    @ColumnInfo(name = "date")
    private String date;


}
