package tn.esprit.restauMobile.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "stock", foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")
})
public class Stock {

    @PrimaryKey(autoGenerate = true)
    private int stockId;

    @ColumnInfo(name = "productName")
    private String productName;

    @ColumnInfo(name = "quantity")
    private int quantity;

    // Relation avec User (un-à-plusieurs)
    private int userId;

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Stock() {
    }

    public Stock(int stockId, String productName, int quantity, int userId) {
        this.stockId = stockId;
        this.productName = productName;
        this.quantity = quantity;
        this.userId = userId;
    }
}

