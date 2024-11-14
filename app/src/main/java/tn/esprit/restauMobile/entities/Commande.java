package tn.esprit.restauMobile.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "commande", foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")
})
public class Commande {

    @PrimaryKey(autoGenerate = true)
    private int commandeId;

    @ColumnInfo(name = "menuId")
    private int menuId;

    // Relation avec User (un-à-plusieurs)
    private int userId;

    @ColumnInfo(name = "date")
    private String date;

    public int getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(int commandeId) {
        this.commandeId = commandeId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
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

    public Commande() {
    }

    public Commande(int menuId, int commandeId, int userId, String date) {
        this.menuId = menuId;
        this.commandeId = commandeId;
        this.userId = userId;
        this.date = date;
    }
}

