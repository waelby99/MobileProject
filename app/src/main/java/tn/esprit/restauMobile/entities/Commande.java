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

    // Getters et Setters
    // ...
}

