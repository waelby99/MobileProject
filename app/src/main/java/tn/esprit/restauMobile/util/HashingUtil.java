package tn.esprit.restauMobile.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.util.Base64;
import java.nio.charset.StandardCharsets;

public class HashingUtil {

    // Hash the password with SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Use android.util.Base64 for API level 25 compatibility
            return Base64.encodeToString(hashBytes, Base64.DEFAULT).trim();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}


