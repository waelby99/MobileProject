package tn.esprit.restauMobile.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.restauMobile.entities.Reclamation;

@Dao
public interface ReclamationDAO {

    @Insert
    void insertReclamation(Reclamation reclamation);

    @Delete
    void deleteReclamation(Reclamation reclamation);

    @Update
    void updateReclamation(Reclamation reclamation);

    @Query("SELECT * FROM reclamation")
    List<Reclamation> getAllReclamations();

    @Query("SELECT * FROM reclamation WHERE reclamationId = :id")
    Reclamation getReclamationById(int id);

    @Query("SELECT * FROM reclamation WHERE userId = :userId")
    List<Reclamation> getReclamationsByUserId(int userId);
}

