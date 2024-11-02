package tn.esprit.restauMobile.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.restauMobile.entities.Commande;

@Dao
public interface CommandeDAO {

    @Insert
    void insertCommande(Commande commande);

    @Delete
    void deleteCommande(Commande commande);

    @Update
    void updateCommande(Commande commande);

    @Query("SELECT * FROM commande")
    List<Commande> getAllCommandes();

    @Query("SELECT * FROM commande WHERE commandeId = :id")
    Commande getCommandeById(int id);

    @Query("SELECT * FROM commande WHERE userId = :userId")
    List<Commande> getCommandesByUserId(int userId);
}
