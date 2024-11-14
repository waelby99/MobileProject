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

    public int getReclamationId() {
        return reclamationId;
    }

    public void setReclamationId(int reclamationId) {
        this.reclamationId = reclamationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Reclamation() {
    }

    public Reclamation(int reclamationId, String message, int userId) {
        this.reclamationId = reclamationId;
        this.message = message;
        this.userId = userId;
    }

}