package tn.esprit.restauMobile.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.restauMobile.entities.Stock;

@Dao
public interface StockDAO {

    @Insert
    void insertStock(Stock stock);

    @Delete
    void deleteStock(Stock stock);

    @Update
    void updateStock(Stock stock);

    @Query("SELECT * FROM stock")
    List<Stock> getAllStocks();

    @Query("SELECT * FROM stock WHERE stockId = :id")
    Stock getStockById(int id);

    @Query("SELECT * FROM stock WHERE userId = :userId")
    List<Stock> getStocksByUserId(int userId);
}
