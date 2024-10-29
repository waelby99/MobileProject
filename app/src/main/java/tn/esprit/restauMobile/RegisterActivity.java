package tn.esprit.restauMobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import tn.esprit.restauMobile.database.AppDataBase;
import tn.esprit.restauMobile.entities.User;
import tn.esprit.restauMobile.util.HashingUtil;

public class RegisterActivity extends Activity {
    EditText eusername, eemail, epassword,ephone;
    Spinner roleSpinner;
    Button register;
    boolean isAllFields = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        eusername = findViewById(R.id.username);
        eemail = findViewById(R.id.email);
        ephone=findViewById(R.id.phone_number);
        epassword = findViewById(R.id.password);
        roleSpinner = findViewById(R.id.role_spinner);
        register = findViewById(R.id.regibutton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                isAllFields= register();
                if(isAllFields)
                {
                    String a= eusername.getText().toString();
                    String b = epassword.getText().toString();
                    Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                    i.putExtra("number1",a);
                    i.putExtra("number2",b);
                    startActivity(i);
                }
            }
        });


    }

    private boolean register() {
        String username = eusername.getText().toString().trim();
        String email = eemail.getText().toString().trim();
        String password = epassword.getText().toString().trim();
        String phone = ephone.getText().toString().trim();
        String role= roleSpinner.getSelectedItem().toString().trim();

        if (checkAllFields(username, email, password, phone)) {
            String hashedPassword = HashingUtil.hashPassword(password);
            User user = new User(username, hashedPassword, email, phone,role);

            AppDataBase db = AppDataBase.getAppDataBase(this);
            db.userDAO().createUser(user);

            Toast.makeText(this, "You have successfully registered", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private boolean checkAllFields (String username, String email, String password,String phonenumber)
    {
        if (TextUtils.isEmpty(username)) {
            eusername.setError("Please enter name");
            return false;

        }
        if (TextUtils.isEmpty(email)) {
            eemail.setError("Please enter proper email");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            epassword.setError("Please enter proper password");
            return false;
        }
        if (TextUtils.isEmpty(phonenumber)) {
            ephone.setError("Please enter proper phone number");
            return false;
        }
        return true;
    }

}
