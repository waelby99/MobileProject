package tn.esprit.restauMobile.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "reclamation",foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId")
})
public class Reclamation {

    @PrimaryKey(autoGenerate = true)
    private int reclamationId;

    @ColumnInfo(name = "message")
    private String message;

    // Relation avec User (un-à-plusieurs)
    private int userId;


}