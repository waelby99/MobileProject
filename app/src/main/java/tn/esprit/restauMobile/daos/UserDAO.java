package tn.esprit.restauMobile.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.lang.annotation.Inherited;
import java.util.List;

import tn.esprit.restauMobile.entities.User;

@Dao
public interface UserDAO {

    @Insert()
    void createUser(User user);

    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user where id =:id")
    User getUserById(int id);

    @Query("SELECT * FROM user WHERE login = :username LIMIT 1")
    User getUserByUsername(String username);

}
