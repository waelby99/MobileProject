package tn.esprit.restauMobile.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.restauMobile.entities.Reservation;

@Dao
public interface ReservationDAO {

    @Insert
    void insertReservation(Reservation reservation);

    @Delete
    void deleteReservation(Reservation reservation);

    @Update
    void updateReservation(Reservation reservation);

    @Query("SELECT * FROM reservation")
    List<Reservation> getAllReservations();

    @Query("SELECT * FROM reservation WHERE reservationId = :id")
    Reservation getReservationById(int id);

    @Query("SELECT * FROM reservation WHERE userId = :userId")
    List<Reservation> getReservationsByUserId(int userId);
}
