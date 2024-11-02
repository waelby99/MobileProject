package tn.esprit.restauMobile.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String login;

    @ColumnInfo
    private String email;

    @ColumnInfo
    private String password;

    @ColumnInfo
    private String numTel;

    @ColumnInfo
    private String role;

    @Ignore
    @Relation(
            entity = Restaurant.class,
            parentColumn = "id",
            entityColumn = "userId"
    )
    private Restaurant restaurant;

    // Relation un-à-plusieurs avec Stock
    @Ignore
    @Relation(
            entity = Stock.class,
            parentColumn = "id",
            entityColumn = "userId"
    )
    private List<Stock> stocks;

    // Relation un-à-plusieurs avec Commande
    @Ignore
    @Relation(
            entity = Commande.class,
            parentColumn = "id",
            entityColumn = "userId"
    )
    private List<Commande> commandes;

    // Relation un-à-plusieurs avec Reservation
    @Ignore
    @Relation(
            entity = Reservation.class,
            parentColumn = "id",
            entityColumn = "userId"
    )
    private List<Reservation> reservations;

    // Relation un-à-plusieurs avec Reclamation
    @Ignore
    @Relation(
            entity = Reclamation.class,
            parentColumn = "id",
            entityColumn = "userId"
    )
    private List<Reclamation> reclamations;


    public User() {
    }

    public User(int id, String login, String email, String password, String numTel, String role) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.role=role;
    }

    public User(String login, String password, String email, String numTel,String role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.numTel = numTel;
        this.role=role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", numTel='" + numTel + '\'' +
                ", role=" + role +
                '}';
    }
}
