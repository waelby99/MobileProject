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

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Reservation(int reservationId, int restaurantId, int userId, String date) {
        this.reservationId = reservationId;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.date = date;
    }

    public Reservation() {
    }
}
