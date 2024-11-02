package tn.esprit.restauMobile.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import tn.esprit.restauMobile.daos.CommandeDAO;
import tn.esprit.restauMobile.daos.MenuDAO;
import tn.esprit.restauMobile.daos.ReclamationDAO;
import tn.esprit.restauMobile.daos.ReservationDAO;
import tn.esprit.restauMobile.daos.RestaurantDAO;
import tn.esprit.restauMobile.daos.StockDAO;
import tn.esprit.restauMobile.daos.UserDAO;
import tn.esprit.restauMobile.entities.Commande;
import tn.esprit.restauMobile.entities.Menu;
import tn.esprit.restauMobile.entities.Reclamation;
import tn.esprit.restauMobile.entities.Reservation;
import tn.esprit.restauMobile.entities.Restaurant;
import tn.esprit.restauMobile.entities.Stock;
import tn.esprit.restauMobile.entities.User;

@Database(entities = {User.class, Stock.class, Restaurant.class, Menu.class, Commande.class, Reservation.class, Reclamation.class}, version=1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public static AppDataBase instance;
    public abstract UserDAO userDAO();
    public abstract StockDAO stockDAO();
    public abstract RestaurantDAO restaurantDAO();
    public abstract MenuDAO menuDAO();
    public abstract CommandeDAO commandeDAO();
    public abstract ReservationDAO reservationDAO();
    public abstract ReclamationDAO reclamationDAO();

    public static  AppDataBase getAppDataBase(Context context){
        if(instance == null ){
            instance= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"DatabaseMobile").allowMainThreadQueries().build();
        }
        return  instance;
    }
}
