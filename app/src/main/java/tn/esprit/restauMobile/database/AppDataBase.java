package tn.esprit.restauMobile.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import tn.esprit.restauMobile.daos.UserDAO;
import tn.esprit.restauMobile.entities.User;

@Database(entities = {User.class}, version=1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public static AppDataBase instance;
    public abstract UserDAO userDAO();
    public static  AppDataBase getAppDataBase(Context context){
        if(instance == null ){
            instance= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"DatabaseMobile").allowMainThreadQueries().build();
        }
        return  instance;
    }
}
